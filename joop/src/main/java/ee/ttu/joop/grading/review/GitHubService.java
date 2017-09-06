package ee.ttu.joop.grading.review;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.egit.github.core.Authorization;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ee.ttu.joop.grading.helpers.FileHandler;

@Service
/**
 * Service that interacts with GitHub servers.
 * @author Maria Kert
 *
 */
public class GitHubService implements ReviewService {

	private ReviewRepository reviewRepository;
	private FileHandler reader;
	private SecureRandom secureRandom;
	private GitHubClient client;

	@Value("${paths.files.repos}")
	private String repoPath;

	@Value("${github.user}")
	private String user;

	@Value("${github.pass}")
	private String pass;
	
	@Value("${github.gistlink}")
	private String gistLink;

	public GitHubService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
		reader = new FileHandler();
		secureRandom = new SecureRandom();
		client = new GitHubClient();
	}

	@Override
	public Review updateReview(Review review) {
		Review rw = reviewRepository.findOne(review.getId());
		String uniid = rw.getStudentTask().getUniid();
		String taskName = rw.getStudentTask().getTask().getName();
		String reviewId = rw.getReviewId();
		if (reviewId == null) {
			reviewId = createGist(uniid, taskName);
		} else {
			reviewId = updateGist(rw.getReviewId(), uniid, taskName);
		}
		rw.setReviewId(reviewId);
		rw.setLink(gistLink + reviewId);
		return reviewRepository.save(rw);
	}

	/**
	 * Saves review to the database using review database interface.
	 * @param review Review to save
	 * @return Saved review
	 */
	public Review saveReview(Review review) {
		return reviewRepository.save(review);
	}

	/**
	 * Creates a new gist from the files associated with the
	 * uniid and task name.
	 * @param uniid Student identification
	 * @param taskName Task identification
	 * @return Gist id
	 */
	private String createGist(String uniid, String taskName) {
		//code snippets from http://bit.ly/2q7fUd1 (shortened GitHub link)
		Authorization auth = getGistAuthorization();
		Gist gist;

		try {
			GistService gistService = new GistService(client);
			gistService.getClient().setOAuth2Token(auth.getToken());

			gist = new Gist();
			gist.setPublic(false);
			gist.setDescription("JOOP");
			Map<String, GistFile> map = addAllFiles(uniid + "/" + taskName);
			gist.setFiles(map);

			gist = gistService.createGist(gist);
		} catch (IOException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return gist.getId();
	}

	/**
	 * Updates an existing gist by uploading new files to it.
	 * @param id Gist identification
	 * @param uniid Student identification
	 * @param taskName Task identification
	 * @return Updated gist id
	 */
	private String updateGist(String id, String uniid, String taskName) {
		Authorization auth = getGistAuthorization();

		//code snippets from http://bit.ly/2q7fUd1 (shortened GitHub link)
		GistService gistService = new GistService(client);
		gistService.getClient().setOAuth2Token(auth.getToken());
		try {
			Gist gist = gistService.getGist(id);
			Map<String, GistFile> map = addAllFiles(uniid + "/" + taskName);
			gist.setFiles(map);
			gistService.updateGist(gist);
			return gist.getId();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Adds all files to a map that can be forwarded to GitHub servers.
	 * @param taskPath Path to the task where all the submission files are
	 * @return Map with every single file associated with the project
	 */
	private Map<String, GistFile> addAllFiles(String taskPath) {
		String path = repoPath + taskPath + "/src/";
		List<File> files = reader.getAllFiles(path);
		Map<String, GistFile> fileMap = new HashMap<>();

		files.forEach(file -> {
			String content = reader.read(file.getAbsolutePath());
			GistFile gistFile = new GistFile();
			gistFile.setFilename(file.getName());
			gistFile.setContent(content);
			fileMap.put(file.getName(), gistFile);
		});
		return fileMap;
	}

	/**
	 * Creates a random string for GitHub authorization.
	 * @return Random string
	 */
	private String getRandomString() {
		return new BigInteger(130, secureRandom).toString(32);
	}

	/**
	 * Gets GitHub authorization for accessing, creating and editing gists.
	 * @return Authorization object if successful, null otherwise.
	 */
	private Authorization getGistAuthorization() {
		//code snippets from http://bit.ly/2q7fUd1 (shortened GitHub link)
		client.setCredentials(user, pass);
		OAuthService oauthService = new OAuthService(client);
		Authorization auth = new Authorization();
		auth.setScopes(Arrays.asList("gist"));
		// no authorization possible without this, has to be unique every time
		auth.setNote(getRandomString());
		try {
			auth = oauthService.createAuthorization(auth);
			return auth;
		} catch (IOException e) {
			return null;
		}
	}
}

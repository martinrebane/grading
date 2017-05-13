package ee.ttu.kert.maria.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.configuration.Configuration;
import ee.ttu.kert.maria.helpers.GitHubService;

@Service
@Transactional
public class ReviewService implements CodeReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	/*private static final String CREATE_PATH = "ruby ../bash/gistcreator.rb";
	private static final String UPDATE_PATH = "ruby ../bash/gistupdater.rb";
	private ScriptRunner scriptRunner = new ScriptRunner();*/
	private static final String REPO_PATH = Configuration.getRepoPath();
	private GitHubService gitHubService = new GitHubService(REPO_PATH + "maria.kert/EX05/src/");
	
	@Override
	public String createLink() {
		return gitHubService.createGist();
	}

	@Override
	public String updateReview(String id) {
		//String updateCommand = UPDATE_PATH + " " + id;
		//return scriptRunner.run(updateCommand);
		return gitHubService.updateGist(id);
	}

	public Review saveReview(Review review) {
		Review databaseReview = reviewRepository.findByUniIdAndTaskName(review.getUniId(), review.getTaskName());
		if (databaseReview == null) {
			String link = createLink();
			review.setReviewLink(link);
			return reviewRepository.save(review);
		}
		return databaseReview;
	}

	public Review getReviewById(long reviewID) {
		return reviewRepository.findOne(reviewID);
	}

	public Iterable<Review> getAllStudentReviews(String uniId) {
		return reviewRepository.findByUniId(uniId);
	}
}

package ee.ttu.kert.maria.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.helpers.GistClient;
import ee.ttu.kert.maria.helpers.ScriptRunner;

@Service
@Transactional
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	/*private static final String CREATE_PATH = "ruby ../bash/gistcreator.rb";
	private static final String UPDATE_PATH = "ruby ../bash/gistupdater.rb";
	private ScriptRunner scriptRunner = new ScriptRunner();*/
	private static final String REPO_PATH = "D:/Users/mammu/workspace/loputoo/repod/";
	private GistClient gistClient = new GistClient(REPO_PATH + "maria.kert/EX05/src/");

	public String createReviewLink() {
		return gistClient.createGist();
		//return scriptRunner.run(CREATE_PATH);
	}

	public String updateReview(String id) {
		//String updateCommand = UPDATE_PATH + " " + id;
		//return scriptRunner.run(updateCommand);
		return gistClient.updateGist(id);
	}

	public Review saveReview(Review review) {
		Review databaseReview = reviewRepository.findByUniIdAndTaskName(review.getUniId(), review.getTaskName());
		if (databaseReview == null) {
			String link = createReviewLink();
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

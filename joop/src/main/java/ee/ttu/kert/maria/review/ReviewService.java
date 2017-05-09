package ee.ttu.kert.maria.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.helpers.ScriptRunner;

@Service
@Transactional
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	private static final String CREATE_PATH = "ruby ../bash/gistcreator.rb";
	private static final String UPDATE_PATH = "ruby ../bash/gistupdater.rb";
	private ScriptRunner scriptRunner = new ScriptRunner();

	public String createReviewLink() {
		return scriptRunner.run(CREATE_PATH);
	}

	public String updateReview(String id) {
		String updateCommand = UPDATE_PATH + " " + id;
		return scriptRunner.run(updateCommand);
	}

	public Review saveReview(Review review) {
		Review databaseReview = reviewRepository.findByUniIdAndTaskName(review.getUniId(), review.getTaskName());
		if (databaseReview == null) {
			String link = createReviewLink();
			review.setGistLink(link);
			return reviewRepository.save(review);
		}
		return databaseReview;
	}

	public Review getReviewById(long gistId) {
		return reviewRepository.findOne(gistId);
	}

	public Iterable<Review> getAllStudentGists(String uniId) {
		return reviewRepository.findByUniId(uniId);
	}
}

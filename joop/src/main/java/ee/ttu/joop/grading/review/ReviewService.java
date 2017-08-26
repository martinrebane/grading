package ee.ttu.joop.grading.review;

/**
 * Interface that handles all student feedback related methods.
 * @author Maria Kert
 *
 */
public interface ReviewService {
	
	/**
	 * Updates review object with either adding a link for the feedback
	 * or updating the files already on the link.
	 * @param review Review object to update
	 * @return Updated review object
	 */
	public Review updateReview(Review review);

}

package ee.ttu.joop.grading.mail;

/**
 * Interface that handles emails and everything associated with it.
 * @author Maria Kert
 *
 */
public interface MailService {
	
	/**
	 * Method to send students feedback for their assignment.
	 * @param uniid Student identification, email username
	 * @param reviewId Id for the review that contains feedback for student
	 * @return Null if there is an error, "sent" otherwise
	 */
	public String sendFeedback(String uniid, String reviewId);

}

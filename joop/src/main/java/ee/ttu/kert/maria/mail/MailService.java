package ee.ttu.kert.maria.mail;

public interface MailService {
	
	public void sendFeedback(String uniid, String reviewLink, String subject);

}
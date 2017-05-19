package ee.ttu.kert.maria.mail;

public interface MailService {
	
	public String sendFeedback(String uniid, String reviewLink, String subject);

}

package ee.ttu.joop.grading.mail;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
/**
 * Service that interacts with MailGun servers.
 * @author Maria Kert
 *
 */
public class MailGunService implements MailService {
	
	@Value("${mailgun.apikey}")
	private String apiKey;
	
	@Value("${github.gistlink}")
	private String gistLink;
	
	private static final String SUBJECT = "Tagasiside";
	
	@Override
	public String sendFeedback(String uniid, String reviewId) {
		ClientResponse response = sendEmail(uniid, reviewId);
		if (response == null) return null;
		return "sent";
	}
	
	/**
	 * Method that interacts with MailGun server.
	 * @param uniid Student identification, email username
	 * @param reviewId Gist identification, student's feedback id
	 * @param subject Email title
	 * @return ClientResponse if successuf, null otherwise
	 */
	private ClientResponse sendEmail(String uniid, String reviewId) {
		//currently MailGun only supports sandbox emails
		String email = uniid + "@ttu.ee";
		try {
			Client client = Client.create();
	        client.addFilter(new HTTPBasicAuthFilter("api", apiKey));
	        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox040c037a" +
	                "e0324f65ae848f4bc645132e.mailgun.org/messages");
	        MultivaluedMapImpl formData = new MultivaluedMapImpl();
	        formData.add("from", "Mailgun Sandbox <postmaster@sandbox040c037ae0324f65ae848f4bc645132e.mailgun.org>");
	        formData.add("to", "Maria Kert <kertmaria@gmail.com>"); //teine argument vahetada email muutujaga
	        formData.add("subject", SUBJECT);
	        formData.add("text", "Tagasiside asub lingil: " + gistLink + reviewId);
	        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
	                post(ClientResponse.class, formData);
		} catch (Exception e) {
			return null;
		}
    }

}

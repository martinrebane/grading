package ee.ttu.kert.maria.mail;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Service
public class MailGunService implements MailService {
	
	@Value("${mailgun.apikey}")
	private String apiKey;
	
	@Value("${github.gistlink}")
	private String gistLink;
	
	@Override
	public String sendFeedback(String uniid, String reviewId, String subject) {
		ClientResponse response = sendEmail(uniid, reviewId, subject);
		if (response == null) return null;
		return "sent";
	}
	
	private ClientResponse sendEmail(String uniid, String reviewId, String subject) {
		try {
			Client client = Client.create();
	        client.addFilter(new HTTPBasicAuthFilter("api", apiKey));
	        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox040c037a" +
	                "e0324f65ae848f4bc645132e.mailgun.org/messages");
	        MultivaluedMapImpl formData = new MultivaluedMapImpl();
	        formData.add("from", "Mailgun Sandbox <postmaster@sandbox040c037ae0324f65ae848f4bc645132e.mailgun.org>");
	        formData.add("to", uniid + " <" + uniid + "@ttu.ee>");
	        formData.add("subject", subject);
	        formData.add("text", "Tagasiside asub lingil: " + gistLink + reviewId);
	        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
	                post(ClientResponse.class, formData);
		} catch (Exception e) {
			return null;
		}
    }
	
	private ClientResponse sendEmailWorking(String uniid, String reviewId, String subject) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", subject));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox040c037a" +
                "e0324f65ae848f4bc645132e.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox040c037ae0324f65ae848f4bc645132e.mailgun.org>");
        formData.add("to", "Maria Kert <kertmaria@gmail.com>");
        formData.add("subject", subject);
        formData.add("text", "Tagasiside asub lingil: " + gistLink + reviewId);
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }

}

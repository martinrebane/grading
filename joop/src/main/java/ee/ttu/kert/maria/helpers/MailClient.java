package ee.ttu.kert.maria.helpers;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MailClient {
	
	private static final String API_KEY = "key-530340de58ee7be39a1358ae50b0d786";
	private String uniid;
	private String reviewLink;
	private String subject = "JOOP tagasiside";
	
	public ClientResponse sendEmail() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", API_KEY));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox040c037a" +
                "e0324f65ae848f4bc645132e.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox040c037ae0324f65ae848f4bc645132e.mailgun.org>");
        formData.add("to", "Maria Kert <kertmaria@gmail.com>");
        formData.add("subject", subject);
        formData.add("text", "Tagasiside asub lingil: " + reviewLink);
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }

	public String getUniid() {
		return uniid;
	}

	public void setUniid(String uniid) {
		this.uniid = uniid;
	}

	public String getReviewLink() {
		return reviewLink;
	}

	public void setReviewLink(String reviewLink) {
		this.reviewLink = reviewLink;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}

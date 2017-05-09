package ee.ttu.kert.maria;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.eclipse.egit.github.core.Authorization;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.OAuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import ee.ttu.kert.maria.helpers.FileReader;
import ee.ttu.kert.maria.sandbox.SandBoxService;

@SpringBootApplication
public class JoopApplication {
	
	static String path = "D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/";
	private static final String API_KEY = "key-530340de58ee7be39a1358ae50b0d786";

	public static void main(String[] args) {
		//SpringApplication.run(JoopApplication.class, args);
		FileReader reader = new FileReader(path);
		System.out.println(reader.getMain());
		SandBoxService service = new SandBoxService();
		service.getMainPath("maria.kert/EX05/src/");
		createGist();
	}
	
	public static ClientResponse sendSimpleMessage() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", API_KEY));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox040c037a" +
                "e0324f65ae848f4bc645132e.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox040c037ae0324f65ae848f4bc645132e.mailgun.org>");
        formData.add("to", "Maria Kert <kertmaria@gmail.com>");
        formData.add("subject", "Hello Maria Kert");
        formData.add("text", "Congratulations Maria Kert, you just sent an email with Mailgun!  You are truly awesome!");
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }
	
	public static void createGist() {
		GitHubClient client = new GitHubClient();
		client.setCredentials("mariakert", "Parool123");
		OAuthService oauthService = new OAuthService(client);

		// Replace with actual login and password
		//oauthService.getClient().setCredentials("mariakert", "Parool123");

		// Create authorization with 'gist' scope only
		Authorization auth = new Authorization();
		auth.setScopes(Arrays.asList("gist"));
		
		//VÄGA OLULINE - see peab erinev olema ka iga kord, panna mingi random string generator külge
		auth.setNote("note2");
		try {
			auth = oauthService.createAuthorization(auth);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Create Gist service configured with OAuth2 token
		GistService gistService = new GistService(client);
		gistService.getClient().setOAuth2Token(auth.getToken());

		// Create Gist
		Gist gist = new Gist();
		gist.setPublic(false);
		gist.setDescription("Created using WOAuth2 token via Java API");
		GistFile file = new GistFile();
		Map<String, GistFile> map = new HashMap<>();
		file.setContent("Gist!");
		file.setFilename("gist.txt");
		map.put(file.getFilename(), file);
		
		GistFile file2 = new GistFile();
		file2.setContent("Gist!");
		file2.setFilename("gist2.txt");
		map.put(file2.getFilename(), file2);
		gist.setFiles(map);
		try {
			gist = gistService.createGist(gist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Created Gist at " + gist.getHtmlUrl());
	}
}

package ee.ttu.kert.maria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ee.ttu.kert.maria.helpers.FileReader;
import ee.ttu.kert.maria.helpers.MailClient;
import ee.ttu.kert.maria.helpers.ScriptRunner;
import ee.ttu.kert.maria.review.ReviewService;
import ee.ttu.kert.maria.sandbox.SandBoxService;

@SpringBootApplication
public class JoopApplication {
	
	//D:\\Program Files\\Git\\git-bash.exe
	
	static String path = "D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/";
	private static final String COMMAND = "cmd /c start ../bash/hashcreator.sh " + path;
	private static final String COMMAND2 = "sh ../bash/zipcreator.sh EX05 agnes.kivistik";

	public static void main(String[] args) {
		//SpringApplication.run(JoopApplication.class, args);
		FileReader reader = new FileReader(path);
		System.out.println(reader.getMain());
		SandBoxService service = new SandBoxService();
		service.getMainPath("maria.kert/EX05/src/");
		ReviewService reviewService = new ReviewService();
		//System.out.println(reviewService.createReviewLink());
		//reviewService.updateReview("51b8b0b76733777a660591bdf371b472");
		/*MailClient client = new MailClient();
		client.setUniid("maria.kert");
		client.setReviewLink("https://gist.github.com/mariakert/51b8b0b76733777a660591bdf371b472");
		client.sendEmail();*/
		
		ScriptRunner runner = new ScriptRunner();
		//selle käivitab windowsi cmd-ga ning üritab selle find käsku kasutada
		runner.run(COMMAND);
		//runner.run(COMMAND2);
	}
}

package ee.ttu.kert.maria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ee.ttu.kert.maria.configuration.Configuration;
import ee.ttu.kert.maria.git.GitService;
import ee.ttu.kert.maria.helpers.FileReader;
import ee.ttu.kert.maria.helpers.ScriptRunner;
import ee.ttu.kert.maria.plagiarism.MossService;
import ee.ttu.kert.maria.review.ReviewService;
import ee.ttu.kert.maria.sandbox.EmbeddablService;
import ee.ttu.kert.maria.submission.Submission;

@SpringBootApplication
public class JoopApplication {

	// D:\\Program Files\\Git\\git-bash.exe

	// static String path =
	// "D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/";
	// static String path =
	// "D:/Users/mammu/workspace/loputoo/loputoo/joop/src/";
	// private static final String COMMAND = "cmd /c start
	// ../bash/hashcreator.sh " + path;
	private static final String COMMAND2 = "../bash/zipcreator.sh";

	public static void main(String[] args) {
		// SpringApplication.run(JoopApplication.class, args);
		/*
		 * FileReader reader = new FileReader(path);
		 * System.out.println(reader.getMain()); /*SandBoxService service = new
		 * SandBoxService(); service.getMainPath("maria.kert/EX05/src/");
		 * ReviewService reviewService = new ReviewService();
		 * //System.out.println(reviewService.createReviewLink());
		 * //reviewService.updateReview("51b8b0b76733777a660591bdf371b472");
		 * /*MailClient client = new MailClient();
		 * client.setUniid("maria.kert"); client.setReviewLink(
		 * "https://gist.github.com/mariakert/51b8b0b76733777a660591bdf371b472")
		 * ; client.sendEmail();
		 */

		ScriptRunner runner = new ScriptRunner();
		// selle käivitab windowsi cmd-ga ning üritab selle find käsku kasutada
		/*String[] command = { "sh", COMMAND2, "EX02", "guhint", Configuration.getRepoPath(),
				Configuration.getZipPath() };
		System.out.println(runner.run(command));*/
		// runner.run(COMMAND2);

		// Submission submission = new Submission();
		/*
		 * GitService service = new GitService();
		 * //System.out.println(service.pull("ago.luberg", "123"));
		 * System.out.println(service.getHash("ago.luberg", "EX01"));
		 * System.out.println(service.createHash("ago.luberg", "EX01"));
		 * //System.out.println(service.getHash("ago.luberg", "EX01"));
		 */

		MossService mossService = new MossService();
		//mossService.transferFiles("chkarn", "EX05");
		System.out.println(mossService.run("EX05"));
	}
}

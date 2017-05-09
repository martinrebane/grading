package ee.ttu.kert.maria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ee.ttu.kert.maria.helpers.FileReader;
import ee.ttu.kert.maria.sandbox.SandBoxService;

@SpringBootApplication
public class JoopApplication {
	
	static String path = "D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/";

	public static void main(String[] args) {
		//SpringApplication.run(JoopApplication.class, args);
		FileReader reader = new FileReader(path);
		System.out.println(reader.getMain());
		SandBoxService service = new SandBoxService();
		service.getMainPath("maria.kert/EX05/src/");
	}
}

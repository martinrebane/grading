package ee.ttu.kert.maria.gist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GistController {
	
	@Autowired
	private GistService gistService;
	
	private static final String COMMAND = "cmd /c start ../bash/testinghook.sh";
	
	@RequestMapping(value="/pull", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Gist pull() {
		try {
			Process process = Runtime.getRuntime().exec(COMMAND);
			process.waitFor();
			
			BufferedReader processIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			
			while ((line = processIn.readLine()) != null) {
                System.out.println(line);
            }
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Interrputed");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other exception");
		}
		return new Gist();
	}
}

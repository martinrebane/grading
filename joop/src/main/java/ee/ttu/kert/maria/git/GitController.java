package ee.ttu.kert.maria.git;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GitController {
	
	private HttpServletRequest request;
	private GitService gitService;
	
	public GitController(HttpServletRequest request, GitService gitService) {
		this.gitService = gitService;
		this.request = request;
	}
	
	@RequestMapping(value="/git/pull/{uniid}/{subjectCode}/", method=RequestMethod.GET)
	public @ResponseBody String pull(@PathVariable String uniid, @PathVariable String subjectCode) {
		/*try {
			BufferedReader reader = request.getReader();
			String json = "";
			String line;
			
			while ((line = reader.readLine()) != null) {
                json += line;
			}
			//System.out.println("JSON: " + json);
			/*JSONObject jsonObject = new JSONObject(json);
			//sellest saaks bitbucketi järgi requesti username'i, et siis git pull õiges kohas teha
			System.out.println(jsonObject.getJSONObject("actor").getString("username"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return gitService.pull(uniid, "");
	}

}

package ee.ttu.kert.maria.git;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.kert.maria.review.Review;

@Controller
public class GitController {
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value="/{uniid}/pull", method=RequestMethod.GET, consumes="application/json")
	public @ResponseBody Review pull() {
		try {
			BufferedReader reader = request.getReader();
			String json = "";
			String line;
			
			while ((line = reader.readLine()) != null) {
                json += line;
			}
			System.out.println("JSON: " + json);
			/*JSONObject jsonObject = new JSONObject(json);
			//sellest saaks bitbucketi järgi requesti username'i, et siis git pull õiges kohas teha
			System.out.println(jsonObject.getJSONObject("actor").getString("username"));*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/* catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return new Review();
	}

}
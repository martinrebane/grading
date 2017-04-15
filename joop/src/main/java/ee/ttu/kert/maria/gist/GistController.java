package ee.ttu.kert.maria.gist;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GistController {
	
	@Autowired
	private GistService gistService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value="/pull", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody Gist pull() {
		try {
			BufferedReader reader = request.getReader();
			String json = "";
			String line;
			
			while ((line = reader.readLine()) != null) {
                json += line;
			}
			JSONObject jsonObject = new JSONObject(json);
			System.out.println(jsonObject.getJSONObject("actor").getString("username"));
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Gist();
	}
}

package ee.ttu.kert.maria.gist;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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
			String line;
			
			while ((line = reader.readLine()) != null) {
                System.out.println(line);
			}
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Gist();
	}
}

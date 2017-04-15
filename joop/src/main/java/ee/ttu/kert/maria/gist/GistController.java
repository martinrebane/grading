package ee.ttu.kert.maria.gist;

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
		System.out.println(request.getAttributeNames());
		return new Gist();
	}
}

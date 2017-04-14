package ee.ttu.kert.maria.gist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GistController {
	
	@Autowired
	private GistService gistService;
	
	@RequestMapping(value="/pull", method=RequestMethod.POST, consumes="application/json")
	public Gist pull() {
		gistService.createGistLink();
		return new Gist();
	}
}

package ee.ttu.kert.maria.central;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CentralController {
	
	private CentralService centralService;
	
	public CentralController(CentralService centralService) {
		this.centralService = centralService;
	}
	
	@RequestMapping(value="/central/{uniid}/{subjectCode}", method=RequestMethod.GET)
	public @ResponseBody String get(@PathVariable String uniid, @PathVariable String subjectCode) {
		centralService.init(uniid, subjectCode);
		return "done";
	}

}

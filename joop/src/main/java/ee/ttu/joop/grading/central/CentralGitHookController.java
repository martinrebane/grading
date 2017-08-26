package ee.ttu.joop.grading.central;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * Central controller activated by Git hook.
 * @author Maria Kert
 *
 */
public class CentralGitHookController {
	
	private CentralGitHookService centralService;
	
	public CentralGitHookController(CentralGitHookService centralService) {
		this.centralService = centralService;
	}
	
	@RequestMapping(value="/central/{uniid}/{subjectCode}", method=RequestMethod.GET)
	public @ResponseBody String init(@PathVariable String uniid, @PathVariable String subjectCode) {
		centralService.init(uniid, subjectCode);
		return "done";
	}

}

package ee.ttu.kert.maria.ide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IDEController {
	
	private EclipseService ideService;
	
	public IDEController(EclipseService ideService) {
		this.ideService = ideService;
	}
	
	@RequestMapping(value="/ide/{uniid}/{taskName}", method=RequestMethod.GET)
	public @ResponseBody String get(@PathVariable String uniid, @PathVariable String taskName) {
		ideService.createProject(uniid, taskName);
		return "done";
	}

}

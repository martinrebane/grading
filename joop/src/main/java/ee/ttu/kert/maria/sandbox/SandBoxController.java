package ee.ttu.kert.maria.sandbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SandBoxController {
	
	private EmbeddablService sandBoxService;
	
	public SandBoxController(EmbeddablService embeddablService) {
		sandBoxService = embeddablService;
	}
	
	@RequestMapping(value="/sandbox/update/{uniid}/{taskName}", method=RequestMethod.POST)
	public SandBox update(@PathVariable String uniid, @PathVariable String taskName, @RequestBody SandBox sandBox) {
		return sandBoxService.updateSandBox(uniid, taskName, sandBox);
	}
}

package ee.ttu.kert.maria.sandbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.ttu.kert.maria.submission.Submission;

@Controller
public class SandBoxController {
	
	private EmbeddablService sandBoxService;
	
	public SandBoxController(EmbeddablService embeddablService) {
		sandBoxService = embeddablService;
	}
	
	@RequestMapping(value="/sandbox/update", method=RequestMethod.POST)
	public SandBox update(Submission submission) {
		return sandBoxService.updateSandBox(submission);
	}
}

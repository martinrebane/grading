package ee.ttu.joop.grading.sandbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.joop.grading.submission.Submission;

@Controller
/**
 * Controller that handles querys about sandbox objects.
 * @author Maria Kert
 *
 */
public class SandBoxController {

	private EmbeddablService embeddablService;

	public SandBoxController(EmbeddablService embeddablService) {
		this.embeddablService = embeddablService;
	}

	@RequestMapping(value = "/sandbox/update", method = RequestMethod.POST)
	public @ResponseBody SandBox update(@RequestBody SandBox sandBox) {
		return embeddablService.update(sandBox);
	}
	
	@RequestMapping(value = "/sandbox/get", method = RequestMethod.GET)
	public @ResponseBody Submission getSubmissionFromQueue() {
		return embeddablService.getSubmissionFromQueue();
	}
}

package ee.ttu.kert.maria.plagiarism;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.kert.maria.task.Task;

@Controller
public class PlagiarismController {
	
	private MossService mossService;

	public PlagiarismController(MossService mossService) {
		this.mossService = mossService;
	}
	
	@RequestMapping(value="/plagiarism/run", method=RequestMethod.POST)
	public @ResponseBody Plagiarism run(@RequestBody Task task) {
		return mossService.run(task);
	}

}

package ee.ttu.joop.grading.plagiarism;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * Controller that handles plagiarism execution and is 
 * user activated.
 * @author Maria Kert
 *
 */
public class PlagiarismController {
	
	private MossService mossService;

	public PlagiarismController(MossService mossService) {
		this.mossService = mossService;
	}
	
	@RequestMapping(value="/plagiarism/run", method=RequestMethod.POST)
	public @ResponseBody Plagiarism run(@RequestBody Plagiarism plagiarism) {
		return mossService.run(plagiarism);
	}

}

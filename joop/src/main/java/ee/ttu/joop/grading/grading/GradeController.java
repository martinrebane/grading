package ee.ttu.joop.grading.grading;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * Controller for grading students' assignments.
 * The controller is user activated only.
 * @author Maria Kert
 *
 */
public class GradeController {
	
	private GradeService gradeService;
	
	public GradeController(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	
	@RequestMapping(value="/grade/update", method=RequestMethod.POST)
	public @ResponseBody Grade update(@RequestBody Grade grade) {
		return gradeService.saveGrade(grade);
	}

}

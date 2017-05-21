package ee.ttu.kert.maria.grading;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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

package ee.ttu.kert.maria.grading;

import org.springframework.stereotype.Controller;

@Controller
public class GradeController {
	
	private GradeService gradeService;
	
	public GradeController(GradeService gradeService) {
		this.gradeService = gradeService;
	}

}

package ee.ttu.kert.maria.grading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GradeController {
	
	@Autowired
	private GradeService gradeService;

}

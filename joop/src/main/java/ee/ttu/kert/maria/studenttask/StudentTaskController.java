package ee.ttu.kert.maria.studenttask;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.kert.maria.submission.Submission;

@Controller
public class StudentTaskController {
	
	private StudentTaskService studentTaskService;
	
	public StudentTaskController(StudentTaskService service) {
		studentTaskService = service;
	}
	
	@RequestMapping(value="/studenttask/{id}", method=RequestMethod.GET)
	public @ResponseBody List<Submission> getSubmissions(@PathVariable Long id) {
		return studentTaskService.getSubmissions(id);
	}

}

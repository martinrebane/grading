package ee.ttu.kert.maria.studenttask;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.ttu.kert.maria.submission.Submission;
import ee.ttu.kert.maria.task.Task;

@Controller
public class StudentTaskController {
	
	@Autowired
	private StudentTaskService studentTaskService;
	
	@RequestMapping(value="/studenttask/{task}/{uniid}", method=RequestMethod.GET, consumes="application/json")
	public List<Submission> getSubmissions(@PathVariable Task task, @PathVariable String uniid) {
		StudentTask studentTask = studentTaskService.getByTaskAndUniid(task, uniid);
		if (studentTask == null) return new ArrayList<>();
		return studentTask.getSubmissions();
	}

}

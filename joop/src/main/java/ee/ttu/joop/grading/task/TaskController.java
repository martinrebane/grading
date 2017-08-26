package ee.ttu.joop.grading.task;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.joop.grading.studenttask.StudentTask;

@Controller
/**
 * Controller that handles queries about Task objects
 * and directs them to TaskService.
 * @author Maria Kert
 *
 */
public class TaskController {
	
	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@RequestMapping(value="/task/{taskName}", method=RequestMethod.GET)
	public @ResponseBody List<StudentTask> getStudentTasks(@PathVariable String taskName) {
		return taskService.getStudentTasks(taskName);
	}
	
	@RequestMapping(value="/task/get", method=RequestMethod.GET)
	public @ResponseBody List<Task> getAllTasks() {
		return taskService.getAll();
	}
}

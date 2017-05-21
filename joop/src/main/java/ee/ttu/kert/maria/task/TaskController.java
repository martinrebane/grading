package ee.ttu.kert.maria.task;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.ttu.kert.maria.studenttask.StudentTask;

@Controller
public class TaskController {
	
	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@RequestMapping(value="/task/{taskName}", method=RequestMethod.GET)
	public List<StudentTask> getStudentTasks(@PathVariable String taskName) {
		return taskService.getStudentTasks(taskName);
	}
	
	@RequestMapping(value="/task/get", method=RequestMethod.GET)
	public Iterable<Task> getAllTasks() {
		return taskService.getAll();
	}
}

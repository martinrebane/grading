package ee.ttu.kert.maria.task;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.kert.maria.studenttask.StudentTask;

@Controller
public class TaskController {
	
	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@RequestMapping(value="/task/{taskName}", method=RequestMethod.GET)
	public @ResponseBody List<StudentTask> getStudentTasks(@PathVariable String taskName) {
		Task task = taskService.getByName(taskName);
		if (task == null) return new ArrayList<>();
		return task.getStudentTasks();
	}
}

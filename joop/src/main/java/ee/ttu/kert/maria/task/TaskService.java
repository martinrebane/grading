package ee.ttu.kert.maria.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ee.ttu.kert.maria.studenttask.StudentTask;

@Service
public class TaskService {
	
	private TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<StudentTask> getStudentTasks(String taskName) {
		Task task = taskRepository.findByName(taskName);
		if (task == null) return new ArrayList<>();
		return task.getStudentTasks();
	}
	
	public Task getByName(String name) {
		return taskRepository.findByName(name);
	}
	
	public Iterable<Task> getAll() {
		return taskRepository.findAll();
	}
	
	public Task save(Task task) {
		return taskRepository.save(task);
	}

}

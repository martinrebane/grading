package ee.ttu.joop.grading.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import ee.ttu.joop.grading.studenttask.StudentTask;

@Service
/**
 * Service that handles all queries about Task objects
 * and directs them to the database interface.
 * @author Maria Kert
 *
 */
public class TaskService {
	
	private TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	/**
	 * Method for getting all StudentTask objects associated
	 * with the task with the given task name.
	 * @param taskName Given task name
	 * @return List of StudentTask objects associated with the task
	 */
	public List<StudentTask> getStudentTasks(String taskName) {
		Task task = taskRepository.findByName(taskName);
		if (task == null) return new ArrayList<>();
		return task.getStudentTasks();
	}
	
	/**
	 * Method for getting a Task object from the database
	 * by given task name.
	 * @param name Given task name
	 * @return Task object if one exists, null otherwise
	 */
	public Task getByName(String name) {
		return taskRepository.findByName(name);
	}
	
	/**
	 * Method for getting all task objects from the database.
	 * @return List of all objects from the database
	 */
	public List<Task> getAll() {
		List<Task> allTasks = new ArrayList<>();
		Iterable<Task> tasks = taskRepository.findAll();
		tasks.forEach(task -> allTasks.add(task));
		allTasks.sort(Comparator.comparing(Task::getName));
		return allTasks;
	}
	
	/**
	 * Method for saving given task to the database.
	 * @param task Given task
	 * @return Saved task object
	 */
	public Task save(Task task) {
		return taskRepository.save(task);
	}

}

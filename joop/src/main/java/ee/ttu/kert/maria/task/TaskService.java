package ee.ttu.kert.maria.task;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
	
	private TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public Task getByName(String name) {
		return taskRepository.findByName(name);
	}
	
	public Task save(Task task) {
		return taskRepository.save(task);
	}

}

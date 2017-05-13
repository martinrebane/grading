package ee.ttu.kert.maria.task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	Task findByName(String name);

}
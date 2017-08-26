package ee.ttu.joop.grading.task;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface that interacts with the database's Task table.
 * @author Maria Kert
 *
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
	
	/**
	 * Method for getting a Task objects from the database by its name.
	 * @param name Task name
	 * @return Task object if one exists, null otherwise
	 */
	Task findByName(String name);

}

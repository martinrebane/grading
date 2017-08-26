package ee.ttu.joop.grading.studenttask;

import org.springframework.data.repository.CrudRepository;

import ee.ttu.joop.grading.task.Task;

/**
 * Interface that interacts with the database's StudentTask table.
 * @author Maria Kert
 *
 */
public interface StudentTaskRepository extends CrudRepository<StudentTask, Long> {
	
	/**
	 * Method for finding a StudentTask object associated with the 
	 * task and uniid in the arguments.
	 * @param task Specified task
	 * @param uniid Student identification
	 * @return StudentTask from the database if one exists, null otherwise
	 */
	StudentTask findByTaskAndUniid(Task task, String uniid);
	
}

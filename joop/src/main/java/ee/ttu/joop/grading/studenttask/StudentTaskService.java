package ee.ttu.joop.grading.studenttask;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ee.ttu.joop.grading.submission.Submission;
import ee.ttu.joop.grading.task.Task;

@Service
/**
 * Service that handles requests about StudentTask objects
 * and directs them to the database interface.
 * @author Maria Kert
 *
 */
public class StudentTaskService {
	
	private StudentTaskRepository repository;
	
	public StudentTaskService(StudentTaskRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Method for getting all submissions associated with
	 * the StudentTask with the given id.
	 * @param id Given id
	 * @return List of submissions associated with the StudentTask
	 */
	public List<Submission> getSubmissions(Long id) {
		StudentTask studentTask = repository.findOne(id);
		if (studentTask == null) return new ArrayList<>();
		return studentTask.getSubmissions();
	}
	
	/**
	 * Method for finding StudentTask object associated with
	 * the given task and uniid
	 * @param task Given task
	 * @param uniid Given uniid
	 * @return StudentTask object if one exists, null otherwise
	 */
	public StudentTask getByTaskAndUniid(Task task, String uniid) {
		return repository.findByTaskAndUniid(task, uniid);
	}
	
	/**
	 * Method for saving StudentTask objects to the database.
	 * @param studentTask Object to save
	 * @return Saved StudentTask object
	 */
	public StudentTask save(StudentTask studentTask) {
		return repository.save(studentTask);
	}

}

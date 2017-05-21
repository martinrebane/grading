package ee.ttu.kert.maria.studenttask;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ee.ttu.kert.maria.submission.Submission;
import ee.ttu.kert.maria.task.Task;

@Service
public class StudentTaskService {
	
	private StudentTaskRepository repository;
	
	public StudentTaskService(StudentTaskRepository repository) {
		this.repository = repository;
	}
	
	public List<Submission> getSubmissions(Long id) {
		StudentTask studentTask = repository.findOne(id);
		if (studentTask == null) return new ArrayList<>();
		return studentTask.getSubmissions();
	}
	
	public StudentTask getByTaskAndUniid(Task task, String uniid) {
		return repository.findByTaskAndUniid(task, uniid);
	}
	
	public StudentTask save(StudentTask studentTask) {
		return repository.save(studentTask);
	}

}

package ee.ttu.kert.maria.studenttask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.task.Task;

@Service
@Transactional
public class StudentTaskService {
	
	@Autowired
	private StudentTaskRepository repository;
	
	public StudentTask getByTaskAndUniid(Task task, String uniid) {
		return repository.findByTaskAndUniid(task, uniid);
	}
	
	public StudentTask save(StudentTask studentTask) {
		return repository.save(studentTask);
	}

}

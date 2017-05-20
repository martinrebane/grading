package ee.ttu.kert.maria.review;

import org.springframework.data.repository.CrudRepository;

import ee.ttu.kert.maria.studenttask.StudentTask;

public interface ReviewRepository extends CrudRepository<Review, Long> {
	
	Review findByStudentTask(StudentTask studentTask);

}

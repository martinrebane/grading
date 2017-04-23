package ee.ttu.kert.maria.review;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
	
	Iterable<Review> findByUniId(String uniId);
	
	Review findByUniIdAndTaskName(String uniId, String taskName);

}
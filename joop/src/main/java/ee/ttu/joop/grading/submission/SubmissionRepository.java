package ee.ttu.joop.grading.submission;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface that interacts with the database's Submission table.
 * @author Maria Kert
 *
 */
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
	
}

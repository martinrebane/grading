package ee.ttu.joop.grading.submission;

import org.springframework.stereotype.Service;

@Service
/**
 * Service that handles all queries about Submission objects
 * and directs them to the database interface.
 * @author Maria Kert
 *
 */
public class SubmissionService {
	
	private SubmissionRepository submissionRepository;
	
	public SubmissionService(SubmissionRepository repository) {
		submissionRepository = repository;
	}
	
	/**
	 * Method for saving Submission objects to the database.
	 * @param submission Object to save
	 * @return Saved Submission object
	 */
	public Submission save(Submission submission) {
		return submissionRepository.save(submission);
	}

}

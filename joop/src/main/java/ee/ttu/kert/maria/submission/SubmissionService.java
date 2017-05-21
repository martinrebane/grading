package ee.ttu.kert.maria.submission;

import org.springframework.stereotype.Service;

@Service
public class SubmissionService {
	
	private SubmissionRepository submissionRepository;
	
	public SubmissionService(SubmissionRepository repository) {
		submissionRepository = repository;
	}
	
	public Submission save(Submission submission) {
		return submissionRepository.save(submission);
	}

}

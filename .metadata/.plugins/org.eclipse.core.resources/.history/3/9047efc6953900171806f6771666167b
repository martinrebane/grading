package ee.ttu.kert.maria.submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubmissionService {
	
	@Autowired
	private SubmissionRepository submissionRepository;
	
	public Submission save(Submission submission) {
		return submissionRepository.save(submission);
	}

}

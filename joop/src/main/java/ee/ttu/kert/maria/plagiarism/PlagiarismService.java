package ee.ttu.kert.maria.plagiarism;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.configuration.Configuration;

@Service
@Transactional
public class PlagiarismService implements AppropriationService {
	
	private static final String PLAGIARISM_PATH = Configuration.getPlagiarismPath();

}

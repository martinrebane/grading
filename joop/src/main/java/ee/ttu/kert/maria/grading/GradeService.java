package ee.ttu.kert.maria.grading;

import org.springframework.stereotype.Service;

@Service
public class GradeService {
	
	private GradeRepository gradeRepository;
	
	public GradeService(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}
	
	public Grade saveGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

}

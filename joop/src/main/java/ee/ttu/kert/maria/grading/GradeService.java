package ee.ttu.kert.maria.grading;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GradeService {
	
	private GradeRepository gradeRepository;
	
	public GradeService(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}
	
	public Grade saveGrade(Grade grade) {
		return gradeRepository.save(grade);
	}
	
	public Grade getGrade(long id) {
		return gradeRepository.findOne(id);
	}
	
	public Iterable<Grade> getAllGrades() {
		return gradeRepository.findAll();
	}

}

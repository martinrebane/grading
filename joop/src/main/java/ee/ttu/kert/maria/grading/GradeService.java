package ee.ttu.kert.maria.grading;

import org.springframework.stereotype.Service;

@Service
public class GradeService {
	
	private GradeRepository gradeRepository;
	
	public GradeService(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}
	
	/**
	 * Method to save given grade to the database.
	 * @param grade Given grade
	 * @return Grade that was saved to the database
	 */
	public Grade saveGrade(Grade grade) {
		Grade gr = gradeRepository.findOne(grade.getId());
		gr.setGrade(grade.getGrade());
		return gradeRepository.save(gr);
	}

}

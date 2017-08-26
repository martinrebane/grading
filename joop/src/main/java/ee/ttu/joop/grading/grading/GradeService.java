package ee.ttu.joop.grading.grading;

import org.springframework.stereotype.Service;

@Service
/**
 * Service that handles queries about Grade objects
 * and directs them to the database interface.
 * @author Maria Kert
 *
 */
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

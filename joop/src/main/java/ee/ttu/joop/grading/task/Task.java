package ee.ttu.joop.grading.task;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ee.ttu.joop.grading.plagiarism.Plagiarism;
import ee.ttu.joop.grading.studenttask.StudentTask;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
/**
 * Upper type of exercise that stores information about
 * the StudentTask and Plagiarism objects associated with it.
 * @author Maria Kert
 *
 */
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String subjectCode;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<StudentTask> studentTasks;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Plagiarism plagiarism;

}

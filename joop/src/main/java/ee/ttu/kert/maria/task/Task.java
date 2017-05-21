package ee.ttu.kert.maria.task;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.kert.maria.plagiarism.Plagiarism;
import ee.ttu.kert.maria.studenttask.StudentTask;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String subjectCode;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonIgnore
	private List<StudentTask> studentTasks;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Plagiarism plagiarism;

}

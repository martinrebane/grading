package ee.ttu.kert.maria.studenttask;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ee.ttu.kert.maria.grading.Grade;
import ee.ttu.kert.maria.review.Review;
import ee.ttu.kert.maria.submission.Submission;
import ee.ttu.kert.maria.task.Task;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StudentTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String uniid;
	
	@ManyToOne
	private Task task;
	
	@OneToOne(mappedBy="studentTask", cascade=CascadeType.ALL)
	private Review review;
	
	@OneToOne(mappedBy="studentTask", cascade=CascadeType.ALL)
	private Grade grade;
	
	@OneToMany(mappedBy="studentTask", cascade=CascadeType.ALL)
	private List<Submission> submissions;

}
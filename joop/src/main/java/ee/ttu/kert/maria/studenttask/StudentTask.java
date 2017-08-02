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

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.kert.maria.grading.Grade;
import ee.ttu.kert.maria.review.Review;
import ee.ttu.kert.maria.submission.Submission;
import ee.ttu.kert.maria.task.Task;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
/**
 * Exercise middle type. Stores information about the task and student 
 * it's associated with, review and grade objects associated with it
 * and a list of submissions.
 * @author Maria Kert
 *
 */
public class StudentTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String uniid;
	
	@ManyToOne
	@JsonIgnore
	private Task task;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Review review;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Grade grade;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Submission> submissions;

}

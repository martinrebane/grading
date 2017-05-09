package ee.ttu.kert.maria.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import ee.ttu.kert.maria.grading.Grade;
import ee.ttu.kert.maria.review.Review;
import ee.ttu.kert.maria.sandbox.SandBox;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Submission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String uniid;
	
	@ManyToOne
	private Task task;
	
	@OneToOne
	private SandBox sandBox;
	
	@OneToOne
	private Review review;
	
	@ManyToOne
	private Grade grade;

}

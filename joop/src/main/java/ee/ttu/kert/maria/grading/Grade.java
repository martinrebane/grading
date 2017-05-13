package ee.ttu.kert.maria.grading;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ee.ttu.kert.maria.studenttask.StudentTask;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Grade {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private double grade;
	
	@OneToOne
	private StudentTask studentTask;

}

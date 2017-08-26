package ee.ttu.joop.grading.grading;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.joop.grading.studenttask.StudentTask;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
/**
 * Grade object.
 * @author Maria Kert
 *
 */
public class Grade {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private double grade;
	
	@OneToOne
	@JsonIgnore
	private StudentTask studentTask;

}

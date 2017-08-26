package ee.ttu.joop.grading.review;

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
 * Object that contains student feedback data.
 * @author Maria Kert
 *
 */
public class Review {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String reviewId;
	
	private String link;
	
	@OneToOne
	@JsonIgnore
	private StudentTask studentTask;

}

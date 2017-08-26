package ee.ttu.joop.grading.plagiarism;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.joop.grading.task.Task;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
/**
 * Plagiarism object.
 * @author Maria Kert
 *
 */
public class Plagiarism {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String result;
	
	@OneToOne
	@JsonIgnore
	private Task task;

}

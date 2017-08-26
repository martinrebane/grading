package ee.ttu.joop.grading.sandbox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.joop.grading.submission.Submission;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
/**
 * Entity to store values needed for running a project and
 * the results of running it.
 * @author Maria Kert
 *
 */
public class SandBox {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String stdout;
	
	private String stderr;
	
	private String location;
	
	private String mainPath;
	
	private String packagePath;
	
	private String classPath;
	
	@OneToOne
	@JsonIgnore
	private Submission submission;

}

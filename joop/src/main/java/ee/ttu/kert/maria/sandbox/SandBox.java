package ee.ttu.kert.maria.sandbox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.kert.maria.submission.Submission;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SandBox {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String stdout;
	
	private String stderr;
	
	private String location;
	
	private String mainPath;
	
	private String packagePath;
	
	@OneToOne
	@JsonIgnore
	private Submission submission;

}

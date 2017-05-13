package ee.ttu.kert.maria.sandbox;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	
	@OneToOne(mappedBy="sandBox", cascade=CascadeType.ALL)
	private Submission submission;

}

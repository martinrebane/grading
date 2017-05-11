package ee.ttu.kert.maria.grading;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ee.ttu.kert.maria.entities.Submission;
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
	
	@OneToMany(mappedBy="grade", cascade=CascadeType.ALL)
	private List<Submission> submissions;

}

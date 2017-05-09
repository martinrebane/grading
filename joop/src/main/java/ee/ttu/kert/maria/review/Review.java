package ee.ttu.kert.maria.review;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ee.ttu.kert.maria.entities.Submission;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String uniId;
	
	private String taskName;
	
	private String reviewLink;
	
	@OneToOne(mappedBy="review", cascade = CascadeType.ALL)
	private Submission submission;

}

package ee.ttu.kert.maria.review;

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
public class Review {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String uniId;
	
	private String taskName;
	
	private String reviewLink;
	
	@OneToOne
	private StudentTask studentTask;

}

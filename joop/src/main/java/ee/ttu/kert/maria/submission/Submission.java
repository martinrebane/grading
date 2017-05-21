package ee.ttu.kert.maria.submission;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ee.ttu.kert.maria.sandbox.SandBox;
import ee.ttu.kert.maria.studenttask.StudentTask;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Submission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String date;
	
	private String location;
	
	@ManyToOne
	@JsonIgnore
	private StudentTask studentTask;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SandBox sandBox;
	
	public Submission() {
		LocalDateTime localDateTime = LocalDateTime.now();
		date = localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
	}
}

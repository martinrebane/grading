package ee.ttu.joop.grading.login;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class User {
	
	private String username;
	private String password;
}

package ee.ttu.joop.grading.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Value("${app.user}")
	private String username;
	
	@Value("${app.pass}")
	private String password;
	
	public LoginService() {}
	
	public boolean login(String user, String pass) {
		if (user.equals(username) && pass.equals(password)) {
			return true;
		}
		return false;
	}

}

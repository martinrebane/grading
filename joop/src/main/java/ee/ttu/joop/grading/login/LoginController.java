package ee.ttu.joop.grading.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(value = "/app/login", method = RequestMethod.POST)
	public @ResponseBody boolean login(@RequestBody User user) {
		return loginService.login(user.getUsername(), user.getPassword());
	}

}

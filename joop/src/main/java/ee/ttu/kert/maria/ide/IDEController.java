package ee.ttu.kert.maria.ide;

import org.springframework.stereotype.Controller;

@Controller
public class IDEController {
	
	private IDEService ideService;
	
	public IDEController(IDEService ideService) {
		this.ideService = ideService;
	}

}

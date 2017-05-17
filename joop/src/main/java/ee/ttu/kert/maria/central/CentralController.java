package ee.ttu.kert.maria.central;

import java.io.BufferedReader;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CentralController {
	
	private CentralService centralService;
	
	public CentralController(CentralService centralService) {
		this.centralService = centralService;
	}
	
	@RequestMapping(value="/central", method=RequestMethod.GET)
	public @ResponseBody String get() {
		try {
			IJavaProject project = centralService.createProject();
			return project.toString();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "something wrong";
	}

}

package ee.ttu.kert.maria.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SandBoxController {
	
	@Autowired
	EmbeddablService sandBoxService;
	
	@RequestMapping(value="/sandbox/{task}/{uniid}", method=RequestMethod.GET, consumes="application/json")
	public @ResponseBody String getMainPath(@PathVariable String task, @PathVariable String uniid) {
		return sandBoxService.getMainPath(uniid + "/" + task + "/src/");
	}

}

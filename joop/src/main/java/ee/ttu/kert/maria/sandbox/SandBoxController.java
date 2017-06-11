package ee.ttu.kert.maria.sandbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SandBoxController {

	private EmbeddablService embeddablService;

	public SandBoxController(EmbeddablService embeddablService) {
		this.embeddablService = embeddablService;
	}

	@RequestMapping(value = "/sandbox/update", method = RequestMethod.POST)
	public @ResponseBody SandBox update(@RequestBody SandBox sandBox) {
		return embeddablService.update(sandBox);
	}
}

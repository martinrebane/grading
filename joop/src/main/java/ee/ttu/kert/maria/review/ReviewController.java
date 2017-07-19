package ee.ttu.kert.maria.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewController {

	private GitHubService gitHubService;

	public ReviewController(GitHubService gitHubService) {
		this.gitHubService = gitHubService;
	}

	@RequestMapping(value = "/review/update", method = RequestMethod.POST)
	public @ResponseBody Review update(@RequestBody Review review) {
		return gitHubService.updateReview(review);
	}

}

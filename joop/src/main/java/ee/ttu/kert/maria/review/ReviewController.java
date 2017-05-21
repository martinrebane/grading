package ee.ttu.kert.maria.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewController {

	private GitHubService reviewService;

	public ReviewController(GitHubService reviewService) {
		this.reviewService = reviewService;
	}

	@RequestMapping(value = "/review/update/{uniid}/{taskName}", method = RequestMethod.POST)
	public @ResponseBody Review update(@RequestBody Review review, @PathVariable String uniid,
			@PathVariable String taskName) {
		return reviewService.updateReview(uniid, taskName, review);
	}

}

package ee.ttu.kert.maria.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * Controller that interacts with the email service.
 * Controller is user activated.
 * @author Maria Kert
 *
 */
public class MailController {

	private MailGunService mailGunService;

	public MailController(MailGunService mailGunService) {
		this.mailGunService = mailGunService;
	}

	@RequestMapping(value="/mail/send/{uniid}/{reviewId}/{subject}", method=RequestMethod.GET)
	public @ResponseBody String send(@PathVariable String uniid, @PathVariable String reviewId,
			@PathVariable String subject) {
		return mailGunService.sendFeedback(uniid, reviewId, subject);
	}

}

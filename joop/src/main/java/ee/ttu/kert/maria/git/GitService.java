package ee.ttu.kert.maria.git;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GitService {
	
	private static final String COMMAND = "cmd /c start ../bash/pullhook.sh maria.kert"; //tulevikus ilmselt cmd /c start --> sh

}

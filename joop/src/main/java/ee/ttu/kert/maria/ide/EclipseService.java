package ee.ttu.kert.maria.ide;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.helpers.FileReader;

@Service
@Transactional
public class EclipseService implements IDEService {
	
	@Value("${paths.files.repos}")
	private String repoPath;

	@Override
	public void createProject(String uniid, String taskName) {
		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		FileReader reader = new FileReader();
		reader.setPath(projectPath);
		String packagePath = reader.getPackagePath();
		
	}
	
	public String zip(String uniid, String taskName) {
		
		return null;
	}

}

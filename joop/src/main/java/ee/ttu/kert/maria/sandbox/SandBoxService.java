package ee.ttu.kert.maria.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.configuration.Configuration;
import ee.ttu.kert.maria.helpers.FileReader;

@Service
@Transactional
public class SandBoxService {
	
	@Autowired
	SandBoxRepository sandBoxRepository;
	private FileReader reader;
	private static final String REPO_PATH = Configuration.getRepoPath();
	private static final String ZIP_PATH = Configuration.getZipPath();
	
	public String getMainPath(String taskPath) {
		reader = new FileReader(REPO_PATH + taskPath);
		String mainPath = reader.getMainPath();
		System.out.println(mainPath);
		/*String classPath = mainPath.substring(0, mainPath.lastIndexOf('/'));
		String mainFile = mainPath.substring(mainPath.lastIndexOf('/') + 1);
		System.out.println(classPath + " " + mainFile);*/
		return mainPath;
	}
	
	public SandBox save(SandBox sandBox) {
		return sandBoxRepository.save(sandBox);
	}
	
	public SandBox getSandBoxById(long id) {
		return sandBoxRepository.findOne(id);
	}

}

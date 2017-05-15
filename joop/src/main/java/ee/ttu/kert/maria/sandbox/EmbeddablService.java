package ee.ttu.kert.maria.sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ee.ttu.kert.maria.helpers.FileReader;

@Service
@Transactional
public class EmbeddablService {
	
	SandBoxRepository sandBoxRepository;
	private FileReader reader;
	
	@Value("${paths.files.repos}")
	private String repoPath;
	
	@Value("${paths.files.zips}")
	private String zipPath;
	
	public EmbeddablService(SandBoxRepository sandBoxRepository) {
		this.sandBoxRepository = sandBoxRepository;
		reader = new FileReader();
	}
	
	public String getMainPath(String taskPath) {
		String projectPath = repoPath + taskPath;
		//reader = new FileReader();
		reader.setPath(projectPath);
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

package ee.ttu.kert.maria.sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ee.ttu.kert.maria.helpers.FileReader;
import ee.ttu.kert.maria.helpers.ScriptRunner;

@Service
@Transactional
public class EmbeddablService implements SandBoxService {
	
	private SandBoxRepository sandBoxRepository;
	private FileReader reader;
	private ScriptRunner scriptRunner;
	
	@Value("${paths.files.repos}")
	private String repoPath;
	
	@Value("${paths.files.zips}")
	private String zipPath;
	
	@Value("${paths.scripts.zip}")
	private String zipScriptPath;
	
	public EmbeddablService(SandBoxRepository sandBoxRepository) {
		this.sandBoxRepository = sandBoxRepository;
		reader = new FileReader();
		scriptRunner = new ScriptRunner();
	}
	
	@Override
	public String sendProject(String uniid, String taskName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
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
	
	@Override
	public String zipProject(String uniid, String taskName) {
		String[] command = {"cmd" , "/c", "start", taskName, uniid, repoPath, zipPath};
		return scriptRunner.run(command);
	}
	
	public SandBox save(SandBox sandBox) {
		return sandBoxRepository.save(sandBox);
	}
	
	public SandBox getSandBoxById(long id) {
		return sandBoxRepository.findOne(id);
	}

}

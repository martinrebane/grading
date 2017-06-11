package ee.ttu.kert.maria.sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ee.ttu.kert.maria.helpers.FileHandler;
import ee.ttu.kert.maria.helpers.ScriptRunner;

@Service
public class EmbeddablService implements SandBoxService {
	
	private SandBoxRepository sandBoxRepository;
	private FileHandler reader;
	private ScriptRunner scriptRunner;
	
	@Value("${paths.files.repos}")
	private String repoPath;
	
	@Value("${paths.files.embeddabl}")
	private String zipPath;
	
	@Value("${paths.scripts.zip}")
	private String zipScriptPath;
	
	@Value("${paths.scripts.embeddabl}")
	private String embeddablScriptPath;
	
	public EmbeddablService(SandBoxRepository sandBoxRepository) {
		this.sandBoxRepository = sandBoxRepository;
		reader = new FileHandler();
		scriptRunner = new ScriptRunner();
	}
	
	@Override
	public SandBox create(String uniid, String taskName) {
		SandBox sandBox = new SandBox();
		String mainPath = getMainPath(uniid, taskName);
		String packagePath = getPackagePath(uniid, taskName);
		sandBox.setMainPath(mainPath);
		sandBox.setPackagePath(packagePath);
		return sandBoxRepository.save(sandBox);
	}
	
	@Override
	public SandBox update(SandBox sandBox) {
		SandBox sb = sandBoxRepository.findOne(sandBox.getId());
		sb.setStdout(sandBox.getStdout());
		sb.setStderr(sandBox.getStderr());
		return sandBoxRepository.save(sb);
	}
	
	@Override
	public String zipProject(String uniid, String taskName) {
		if (!zipPath.endsWith("/")) zipPath += "/";
		if (!repoPath.endsWith("/")) repoPath += "/";
		String[] command = {"bash", embeddablScriptPath, taskName, uniid, repoPath, zipPath};
		String source = scriptRunner.run(command);
		source = source.substring(0, source.lastIndexOf("/"));
		String[] command2 = {"bash", zipScriptPath, uniid, taskName, source, zipPath + taskName};
		return scriptRunner.run(command2);
	}
	
	public SandBox save(SandBox sandBox) {
		return sandBoxRepository.save(sandBox);
	}

	private String getMainPath(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		String mainPath = reader.getMainPath(projectPath.replace("/mnt/d", "D:"));
		return mainPath;
	}
	
	private String getPackagePath(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		String path = repoPath + uniid + "/" + taskName + "/src/";
		return reader.getPackagePath(path.replace("/mnt/d", "D:"));
	}
}

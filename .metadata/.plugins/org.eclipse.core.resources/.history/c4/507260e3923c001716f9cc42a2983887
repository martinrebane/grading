package ee.ttu.kert.maria.git;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ee.ttu.kert.maria.helpers.FileReader;
import ee.ttu.kert.maria.helpers.ScriptRunner;

@Service
@Transactional
public class GitService implements VersionControlService {
	
	@Value("${paths.scripts.pull}")
	private String pullScriptPath;
	
	@Value("${paths.scripts.hash}")
	private String hashScriptPath;
	
	@Value("${paths.files.repos}")
	private String repoPath;
	
	@Value("${paths.files.hash}")
	private String hashPath;
	
	private ScriptRunner scriptRunner;
	
	public GitService() {
		scriptRunner = new ScriptRunner();
	}
	
	@Override
	public String pull(String uniid, String subjectCode) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		String[] command = {"bash", pullScriptPath, uniid, repoPath};
		scriptRunner = new ScriptRunner();
		return scriptRunner.run(command);
	}

	@Override
	public String getHash(String uniid, String taskName) {
		if (!hashPath.endsWith("/")) hashPath += "/";
		String path = hashPath + uniid + "/" + taskName;
		FileReader reader = new FileReader();
		path += ".txt";
		return reader.read(path);
	}

	@Override
	public String createHash(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		if (!hashPath.endsWith("/")) hashPath += "/";
		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		scriptRunner = new ScriptRunner();
		String[] command = {"bash", hashScriptPath, projectPath, hashPath};
		return scriptRunner.run(command);
	}
}

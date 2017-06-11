package ee.ttu.kert.maria.git;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ee.ttu.kert.maria.helpers.FileHandler;
import ee.ttu.kert.maria.helpers.ScriptRunner;

@Service
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
	public boolean hasChanged(String uniid, String taskName) {
		String currentHash = getHash(uniid, taskName);
		String newHash = createHash(uniid, taskName);
		return currentHash != newHash;
	}

	private String getHash(String uniid, String taskName) {
		if (!hashPath.endsWith("/")) hashPath += "/";
		String path = hashPath + uniid + "/" + taskName + ".txt";
		path = path.replace("/mnt/d", "D:");
		FileHandler reader = new FileHandler();
		String ret = reader.read(path);
		if (ret != null) ret.replaceAll("\n", "");
		return ret;
	}

	private String createHash(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		if (!hashPath.endsWith("/")) hashPath += "/";
		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		scriptRunner = new ScriptRunner();
		String[] command = {"bash", hashScriptPath, projectPath, hashPath};
		String ret = scriptRunner.run(command);
		if (ret != null) ret.replaceAll("\n", "");
		return ret;
	}
}

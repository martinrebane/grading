package ee.ttu.kert.maria.ide;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.helpers.FileReader;
import ee.ttu.kert.maria.helpers.ScriptRunner;

@Service
@Transactional
public class EclipseService implements IDEService {
	
	@Value("${paths.files.repos}")
	private String repoPath;
	
	@Value("${paths.files.zips}")
	private String zipPath;
	
	@Value("${paths.scripts.zip}")
	private String zipScriptPath;
	
	@Value("${paths.scripts.project}")
	private String projectCreatorPath;
	
	@Value("${paths.templates.project}")
	private String projectTemplatePath;
	
	private ScriptRunner scriptRunner;
	
	public EclipseService() {
		scriptRunner = new ScriptRunner();
	}

	@Override
	public void createProject(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		if (!zipPath.endsWith("/")) zipPath += "/";
		if (!projectTemplatePath.endsWith("/")) projectTemplatePath += "/";
		
		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		String templateFilePath = projectTemplatePath + ".project";
		templateFilePath = templateFilePath.replace("/mnt/d", "D:");
		
		File templateFile = new File(templateFilePath);
		FileReader reader = new FileReader();
		List<String> lines = reader.readAllLines(templateFile);
		
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).trim().startsWith("<name>")) {
				String toBeReplaced = lines.get(i).trim();
				String replaceString = "<name>" + taskName + "</name>";
				String newLine = lines.get(i).replace(toBeReplaced, replaceString);
				lines.remove(i);
				lines.add(i, newLine);
				break;
			}
		}
		System.out.println(lines);
	}
	
	public String zip(String uniid, String taskName) {
		
		return null;
	}
	
	private void writeFile() {
		
	}

}

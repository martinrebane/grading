package ee.ttu.joop.grading.ide;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ee.ttu.joop.grading.helpers.FileHandler;
import ee.ttu.joop.grading.helpers.ScriptRunner;

@Service
/**
 * Eclipse based IDE service.
 * @author Maria Kert
 *
 */
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
	public String createProject(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		if (!zipPath.endsWith("/")) zipPath += "/";
		if (!projectTemplatePath.endsWith("/")) projectTemplatePath += "/";

		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		String templateFilePath = projectTemplatePath + ".project";
		//templateFilePath = templateFilePath.replace("/mnt/d", "D:");

		FileHandler handler = new FileHandler();
		List<String> lines = handler.readAllLines(templateFilePath);
		lines = replaceLine(lines, "<name>", "<name>" + taskName + "</name>");

		String[] createProject = new String[] { "bash", projectCreatorPath, taskName, uniid, repoPath, zipPath,
				projectTemplatePath };
		String newProjectFolder = scriptRunner.run(createProject);

		boolean written = handler.writeLines(lines, newProjectFolder + "/.project");

		if (!written) return null;
		
		String[] zipProject = new String[] {"bash", zipScriptPath, uniid, taskName, projectPath, zipPath + taskName};
		String result = scriptRunner.run(zipProject).split("static")[1];
		return result;
	}

	/**
	 * Method to replace a line in a list of lines.
	 * @param lines List of lines
	 * @param search Line to change
	 * @param replace Line to replace with
	 * @return List of lines with the replaced line
	 */
	private List<String> replaceLine(List<String> lines, String search, String replace) {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).trim().startsWith(search)) {
				String toBeReplaced = lines.get(i).trim();
				String replaceString = replace;
				String newLine = lines.get(i).replace(toBeReplaced, replaceString);
				lines.remove(i);
				lines.add(i, newLine);
				break;
			}
		}
		return lines;
	}
}

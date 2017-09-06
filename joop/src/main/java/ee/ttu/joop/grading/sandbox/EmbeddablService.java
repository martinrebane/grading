package ee.ttu.joop.grading.sandbox;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ee.ttu.joop.grading.helpers.FileHandler;
import ee.ttu.joop.grading.helpers.ScriptRunner;
import ee.ttu.joop.grading.submission.Submission;

@Service
/**
 * Service that handles queries about SandBox objects.
 * @author Maria Kert
 *
 */
public class EmbeddablService implements SandBoxService {
	
	private SandBoxRepository sandBoxRepository;
	private FileHandler reader;
	private ScriptRunner scriptRunner;
	private Queue<Submission> embeddablQueue;
	
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
		embeddablQueue = new LinkedList<>();
	}
	
	@Override
	public SandBox create(String uniid, String taskName) {
		SandBox sandBox = new SandBox();
		String mainPath = getMainPath(uniid, taskName);
		String packagePath = getPackagePath(uniid, taskName);
		String classPath = getClassPath(mainPath);
		sandBox.setMainPath(mainPath);
		sandBox.setPackagePath(packagePath);
		sandBox.setClassPath(classPath);
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
		String result = scriptRunner.run(command2).split("static")[1];
		return result;
	}
	
	@Override
	public void addSubmissionToQueue(Submission submission) {
		if (submission!= null) embeddablQueue.add(submission);
	}

	@Override
	public Submission getSubmissionFromQueue() {
		if (embeddablQueue.peek() == null) {
			return null;
		}
		return embeddablQueue.remove();
	}
	
	/**
	 * Method to save sandbox objects to the database.
	 * @param sandBox Object to save
	 * @return Saved sandbox object
	 */
	public SandBox save(SandBox sandBox) {
		return sandBoxRepository.save(sandBox);
	}

	/**
	 * Method for getting main class path from the specified task.
	 * @param uniid Student identification
	 * @param taskName Task identification
	 * @return Path to main class
	 */
	private String getMainPath(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		String mainPath = reader.getMainPath(projectPath);
		
		if (mainPath == null) return null;
		return uniid + "/" + mainPath;
	}
	
	/**
	 * Method for getting package path to main class of the specified task.
	 * @param uniid Student identification
	 * @param taskName Task identification
	 * @return Package path to main class
	 */
	private String getPackagePath(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		String path = repoPath + uniid + "/" + taskName + "/src/";
		return reader.getPackagePath(path);
	}
	
	/**
	 * Method for getting the class path for running the project.
	 * @param mainPath Project's main class path
	 * @return Class path to run the project
	 */
	private String getClassPath(String mainPath) {
		if (mainPath == null) return null;
		String[] folders = mainPath.split("/");
		if (folders.length == 2) return folders[0];
		return folders[0] + "/" + folders[1];
	}

}

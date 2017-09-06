package ee.ttu.joop.grading.plagiarism;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ee.ttu.joop.grading.helpers.FileHandler;
import ee.ttu.joop.grading.helpers.ScriptRunner;
import it.zielke.moji.MossException;
import it.zielke.moji.SocketClient;
import it.zielke.moji.Stage;

@Service
/**
 * Plagiarism service using MOSS (Measure of Software Similarity) technology.
 * @author Maria Kert
 *
 */
public class MossService implements PlagiarismService {

	private PlagiarismRepository plagiarismRepository;

	@Value("${paths.files.plagiarism}")
	private String plagiarismPath;

	@Value("${paths.files.repos}")
	private String repoPath;

	@Value("${moss.userid}")
	private String mossUserid;

	@Value("${paths.scripts.copy}")
	private String copyScriptPath;

	public MossService(PlagiarismRepository plagiarismRepository) {
		this.plagiarismRepository = plagiarismRepository;
	}

	@Override
	public Plagiarism run(Plagiarism plagiarism) {
		Plagiarism plag = plagiarismRepository.findOne(plagiarism.getId());
		String taskName = plag.getTask().getName();
		
		if (!plagiarismPath.endsWith("/")) plagiarismPath += "/";
		String path = plagiarismPath + taskName;
		
		//code from https://github.com/nordicway/moji
		Collection<File> studentFiles = FileUtils.listFiles(new File(path), new String[] { "java" }, true);
		SocketClient client = new SocketClient();
		client.setUserID(mossUserid);
		try {
			client.setLanguage("java");
			client.run();
			for (File file : studentFiles) {
				client.uploadFile(file);
			}
			System.out.println(client.getCurrentStage() == Stage.AWAITING_QUERY);
			client.sendQuery();
			URL results = client.getResultURL();
			Plagiarism pl = plagiarismRepository.findOne(plagiarism.getId());
			pl.setResult(results.toString());
			return plagiarismRepository.save(pl);
		} catch (MossException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void transferFiles(String uniid, String taskName) {
		if (!repoPath.endsWith("/")) repoPath += "/";
		if (!plagiarismPath.endsWith("/")) plagiarismPath += "/";
		String projectPath = repoPath + uniid + "/" + taskName + "/src/";
		String destPath = plagiarismPath + taskName + "/" + uniid + "/";

		FileHandler reader = new FileHandler();
		List<File> files = reader.getAllFiles(projectPath);
		ScriptRunner scriptRunner = new ScriptRunner();
		List<String> folderPaths = new ArrayList<>();

		for (File file : files) {
			String fullPath = file.getAbsolutePath();
			String folderPath = fullPath.substring(0, fullPath.lastIndexOf("/") + 1);
			if (!folderPaths.contains(folderPath)) {
				folderPaths.add(folderPath);
			}
		}

		for (String folderPath : folderPaths) {
			String[] command = { "bash", copyScriptPath, folderPath, destPath };
			scriptRunner.run(command);
		}
	}
}

package ee.ttu.kert.maria.plagiarism;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.kert.maria.configuration.Configuration;
import it.zielke.moji.MossException;
import it.zielke.moji.SocketClient;

@Service
@Transactional
public class MossService implements PlagiarismService {
	
	@Autowired
	private PlagiarismRepository plagiarismRepository;
	
	private static final String PLAGIARISM_PATH = Configuration.getPlagiarismPath();
	private static final String MOSS_USERID = Configuration.getMossUserid();

	@Override
	public String run(String taskName) {
		String path = PLAGIARISM_PATH + taskName;
		Collection<File> studentFiles = FileUtils.listFiles(new File(path), new String[] {"java"}, true);
		SocketClient client = new SocketClient();
		client.setUserID(MOSS_USERID);
		try {
			client.setLanguage("java");
			client.run();
			for (File file : studentFiles) {
				client.uploadFile(file);
			}
			URL results = client.getResultURL();
			return results.toString();
		} catch (MossException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

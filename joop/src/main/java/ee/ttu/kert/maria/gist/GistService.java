package ee.ttu.kert.maria.gist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ee.ttu.kert.maria.script.GistCreator;

@Service
@Transactional
public class GistService {
	
	@Autowired
	GistRepository gistRepository;
	
	public String createGistLink() {
		GistCreator creator = new GistCreator();
		return creator.createGistLink();
	}
	
	public Gist saveGist(Gist gist) {
		Gist databaseGist = gistRepository.findByUniIdAndTaskName(gist.getUniId(), gist.getTaskName());
		if (databaseGist == null) {
			String link = createGistLink();
			gist.setGistLink(link);
			return gistRepository.save(gist);
		}
		return databaseGist;
	}
	
	public Gist getGistById(long gistId) {
		return gistRepository.findOne(gistId);
	}
	
	public Iterable<Gist> getAllStudentGists(String uniId) {
        return gistRepository.findByUniId(uniId);
    }
}

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
	
	public String getGistLink() {
		GistCreator creator = new GistCreator();
		creator.run();
		return creator.getLink();
	}
	
	public Gist saveGist(Gist gist) {
		//siin käivita gist generator ja salvesta link
		return gistRepository.save(gist);
	}
	
	public Gist getGistById(long gistId) {
		return gistRepository.findOne(gistId);
	}
	
	public Iterable<Gist> getAllStudentGists(String uniId) {
        return gistRepository.findByUniId(uniId);
    }

}

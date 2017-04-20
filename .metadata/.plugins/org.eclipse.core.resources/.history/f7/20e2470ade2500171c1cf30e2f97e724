package ee.ttu.kert.maria.gist;

import org.springframework.data.repository.CrudRepository;

public interface GistRepository extends CrudRepository<Gist, Long> {
	
	Iterable<Gist> findByUniId(String uniId);
	
	Gist findByUniIdAndTaskName(String uniId, String taskName);

}

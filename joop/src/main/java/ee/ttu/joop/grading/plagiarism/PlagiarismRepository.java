package ee.ttu.joop.grading.plagiarism;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface that interacts with the database's Plagiarism table.
 * @author Maria Kert
 *
 */
public interface PlagiarismRepository extends CrudRepository<Plagiarism, Long> {

}

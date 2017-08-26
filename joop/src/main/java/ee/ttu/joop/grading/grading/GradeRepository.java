package ee.ttu.joop.grading.grading;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface that interacts with the database's Grade table.
 * @author Maria Kert
 *
 */
public interface GradeRepository extends CrudRepository<Grade, Long> {

}

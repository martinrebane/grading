package ee.ttu.joop.grading.review;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface that interacts with the database's Review table.
 * @author Maria Kert
 *
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {

}

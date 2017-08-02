package ee.ttu.kert.maria.review;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface that interacts with the database's Review table.
 * @author Maria Kert
 *
 */
public interface ReviewRepository extends CrudRepository<Review, Long> {

}

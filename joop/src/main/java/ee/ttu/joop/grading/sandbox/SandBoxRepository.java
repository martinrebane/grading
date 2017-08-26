package ee.ttu.joop.grading.sandbox;

import org.springframework.data.repository.CrudRepository;

/**
 * Interace that interacts with the database's SandBox table.
 * @author Maria Kert
 *
 */
public interface SandBoxRepository extends CrudRepository<SandBox, Long> {

}

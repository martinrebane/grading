package ee.ttu.joop.grading.ide;

/**
 * Interface for IDE interaction.
 * @author Maria Kert
 *
 */
public interface IDEService {
	
	/**
	 * A method to create an IDE project.
	 * @param uniid Student identification, part of project's path
	 * @param taskName Task identification, part of project's path
	 * @return Project location in file system
	 */
	public String createProject(String uniid, String taskName);

}

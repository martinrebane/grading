package ee.ttu.joop.grading.git;

/**
 * Interface for version control.
 * @author Maria Kert
 *
 */
public interface VersionControlService {
	
	/**
	 * Method for pulling updates from Git using a script.
	 * @param uniid Part of the script command, student identification
	 * @param subjectCode Part of the script command, subject identification
	 * @return Results of the script
	 */
	public String pull(String uniid, String subjectCode);
	
	/**
	 * Method for checking whether the file system's has has changed
	 * using a script.
	 * @param uniid Part of the script command, student identification
	 * @param taskName Part of the script command, task identification
	 * @return true if has changed, false if not
	 */
	public boolean hasChanged(String uniid, String taskName);

}

package ee.ttu.joop.grading.plagiarism;

/**
 * Interface that describes methods needed for running the plagiarism service.
 * @author Maria Kert
 *
 */
public interface PlagiarismService {
	
	/**
	 * Runs plagiarism.
	 * @param plagiarism Plagiarism object to update
	 * @return Updated plagiarism object
	 */
	public Plagiarism run(Plagiarism plagiarism);
	
	/**
	 * Transfers files to the specified hierarchy for the plagiarism service to work.
	 * @param uniid Student identification, part of plagiarism file path
	 * @param taskName Task identification, part of plagiarism file path
	 */
	public void transferFiles(String uniid, String taskName);

}

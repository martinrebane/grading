package ee.ttu.joop.grading.sandbox;

import ee.ttu.joop.grading.submission.Submission;

/**
 * Interface that handles SandBox object creation, 
 * updating and compressing into the right format.
 * @author Maria Kert
 *
 */
public interface SandBoxService {
	
	/**
	 * Creates a new sandbox instance for specific student and task.
	 * @param uniid Student identification
	 * @param taskName Task identification
	 * @return New sandbox object
	 */
	public SandBox create(String uniid, String taskName);
	
	/**
	 * Updates sandbox object with the data from the argument.
	 * @param sandBox Argument with the data needed for updating
	 * @return Updated sandbox object
	 */
	public SandBox update(SandBox sandBox);
	
	/**
	 * Method to compress current project files into a zip file.
	 * @param uniid Student identification
	 * @param taskName Task identification
	 * @return Zip file location
	 */
	public String zipProject(String uniid, String taskName);
	
	/**
	 * Adds submission to queue in order to run it.
	 * @param submission Submission to add.
	 */
	public void addSubmissionToQueue(Submission submission);
	
	/**
	 * Gets first added submission from the queue.
	 * @return Submission from the queue
	 */
	public Submission getSubmissionFromQueue();

}

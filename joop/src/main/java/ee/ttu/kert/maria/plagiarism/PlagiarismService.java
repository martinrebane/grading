package ee.ttu.kert.maria.plagiarism;

import ee.ttu.kert.maria.task.Task;

public interface PlagiarismService {
	
	public Plagiarism run(Task task);
	
	public void transferFiles(String uniid, String taskName);

}

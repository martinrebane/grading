package ee.ttu.kert.maria.sandbox;

import ee.ttu.kert.maria.submission.Submission;

public interface SandBoxService {
	
	public SandBox updateSandBox(Submission submission);
	
	public String zipProject(String uniid, String taskName);

}

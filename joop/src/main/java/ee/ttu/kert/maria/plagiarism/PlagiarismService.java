package ee.ttu.kert.maria.plagiarism;

public interface PlagiarismService {
	
	public Plagiarism run(String taskName, Plagiarism plagiarism);
	
	public void transferFiles(String uniid, String taskName);

}

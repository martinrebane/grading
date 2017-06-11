package ee.ttu.kert.maria.git;

public interface VersionControlService {
	
	public String pull(String uniid, String subjectCode);
	
	public boolean hasChanged(String uniid, String taskName);

}

package ee.ttu.kert.maria.sandbox;

public interface SandBoxService {
	
	public SandBox updateSandBox(String uniid, String taskName, SandBox sandBox);
	
	public String zipProject(String uniid, String taskName);

}

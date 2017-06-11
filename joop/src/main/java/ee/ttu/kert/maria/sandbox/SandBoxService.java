package ee.ttu.kert.maria.sandbox;

public interface SandBoxService {
	
	public SandBox create(String uniid, String taskName);
	
	public SandBox update(SandBox sandBox);
	
	public String zipProject(String uniid, String taskName);

}

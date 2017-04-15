package ee.ttu.kert.maria.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitPull {
	
	private static final String COMMAND = "cmd /c start ../bash/pullhook.sh"; //tulevikus ilmselt cmd /c start --> sh
	
	public void pull() {
		try {
			Process process = Runtime.getRuntime().exec(COMMAND);
			process.waitFor();
			
			BufferedReader processIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			
			while ((line = processIn.readLine()) != null) {
                System.out.println(line);
            }
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Interrputed");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other exception");
		}
	}

}

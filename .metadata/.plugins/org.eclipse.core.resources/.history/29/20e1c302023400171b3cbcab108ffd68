package ee.ttu.kert.maria.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptRunner {
	
	public String run(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			
			BufferedReader processIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			
			while ((line = processIn.readLine()) != null) {
                return line;
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
		return null;
	}

}

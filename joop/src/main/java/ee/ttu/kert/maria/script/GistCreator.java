package ee.ttu.kert.maria.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GistCreator {
	
	private static final String CREATE_PATH = "ruby ../bash/gistcreator.rb";
	private static final String UPDATE_PATH = "ruby ../bash/gistupdater.rb";
	
	public String createGistLink() {
		try {
			Process process = Runtime.getRuntime().exec(CREATE_PATH);
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
	
	public void updateGist(String gistId) {
		try {
			String command = UPDATE_PATH + " " + gistId;
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			
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

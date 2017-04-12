package ee.ttu.kert.maria.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GistCreator {
	
	private String link;
	
	public void run() {
		try {
			Process process = Runtime.getRuntime().exec("ruby ../bash/gistcreator.rb");
			process.waitFor();
			
			BufferedReader processIn = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			
			while ((line = processIn.readLine()) != null) {
                link = line;
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

	public String getLink() {
		return link;
	}
}

package ee.ttu.joop.grading.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class for running Bash scripts.
 * @author Maria Kert
 *
 */
public class ScriptRunner {

	/**
	 * Method to run a script with given commands.
	 * @param command Given commands
	 * @return Script results if successful, null otherwise
	 */
	public String run(String[] command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();

			BufferedReader processIn = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String ret = "";
			String line;

			while ((line = processIn.readLine()) != null) {
				ret += line;
			}

			/*BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = stdError.readLine()) != null) {
			    System.out.println(line);
			}*/

			if (ret.equals("")) {
				return null;
			}
			return ret;
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Interrputed");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Other exception");
		}
		return null;
	}

}

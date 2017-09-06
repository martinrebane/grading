package ee.ttu.joop.grading.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading from and writing to a file.
 * @author Maria Kert
 *
 */
public class FileHandler {

	private File startFolder;
	
	/**
	 * Method to get main class path from the project path.
	 * @param path Project path
	 * @return Main path for the project if exists, null otherwise
	 */
	public String getMainPath(String path) {
		setPath(path);
		return getMainPath(startFolder);
	}
	
	/**
	 * Gets all files from given folder path.
	 * @param path Folder path
	 * @return List of all files if any exist, empty list otherwise
	 */
	public List<File> getAllFiles(String path) {
		setPath(path);
		return getAllFiles(startFolder, new ArrayList<>());
	}
	
	/**
	 * Method to read a file from the given file path.
	 * @param filePath Given file path
	 * @return Contents of the file as a String. Null if 
	 * there is an error, file is empty or file path doesn't
	 * exist
	 */
	public String read(String filePath) {
		if (filePath == null) return null;
		
		File file = new File(filePath);
		Path path = Paths.get(file.getAbsolutePath());

		String result = "";

		try {
			List<String> lines = Files.readAllLines(path);
			for (String line : lines) {
				result += line + "\n";
			}

			if ("".equals(result)) {
				return null;
			}
			return result;
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Method to read all lines from given file path.
	 * @param filePath Given file path
	 * @return List of every single line from the file;
	 * null if there is an error or file path doesn't exist
	 */
	public List<String> readAllLines(String filePath) {
		if (filePath == null) return null;
		
		File file = new File(filePath);
		Path path = Paths.get(file.getAbsolutePath());

		try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Method to get the package path for the class that
	 * contains main method.
	 * @param path Project path
	 * @return Package path if one exists, empty String if
	 * default package, null otherwise
	 */
	public String getPackagePath(String path) {
		setPath(path);
		String mainPath = getMainPath(startFolder);
		if (mainPath == null) return null;
		
		if (mainPath.indexOf("/") == mainPath.lastIndexOf("/")) {
			return "";
		} else {
			return mainPath.substring(mainPath.indexOf("/") + 1, mainPath.lastIndexOf("/")).replaceAll("/", ".");
		}
	}
	
	/**
	 * Writes lines to a file with the given path.
	 * @param lines Lines to write
	 * @param path File path
	 * @return true if successful, false otherwise
	 */
	public boolean writeLines(List<String> lines, String path) {
		Path p = Paths.get(path);
		try {
			Files.write(p, lines, StandardCharsets.UTF_8);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Setter for startFolder.
	 * @param path File path
	 */
	private void setPath(String path) {
		File startFolder = new File(path);
		this.startFolder = startFolder;
	}
	
	/**
	 * Method to get all files in the given folder and subfolders.
	 * @param folder Starting folder for getting files 
	 * @param files List to store all files
	 * @return List of files in the folder and subfolders
	 */
	private List<File> getAllFiles(File folder, List<File> files) {
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				files.add(file);
			} else {
				return getAllFiles(file, files);
			}
		}
		return files;
	}
	
	/**
	 * Method for getting current project's main class path.
	 * @param folder Project folder
	 * @return Main class path if main method exists, null otherwise
	 */
	private String getMainPath(File folder) {
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String absolutePath = file.getAbsolutePath();
				if (isMain(absolutePath)) {
					return file.getName();
				}
			} else {
				String finalPath = getMainPath(file);
				if (finalPath != null) {
					if (folder.equals(startFolder)) {
						String absolutePath = folder.getAbsolutePath();
						String[] pathFolders = absolutePath.split("/");
						String taskFolder = pathFolders[pathFolders.length - 2] + "/";
						return taskFolder + file.getName() + "/" + finalPath;
					}
					return file.getName() + "/" + finalPath;
				}
			}
		}
		return null;
	}

	/**
	 * Method to determine whether given file contains main method.
	 * @param path File path
	 * @return true if file contains main method, false if not
	 */
	private boolean isMain(String path) {
		Path filePath = Paths.get(path);
		String result;
		try {
			List<String> lines = Files.readAllLines(filePath);
			result = lines.stream().filter(line -> line.trim().startsWith("public static void main(String[] args)"))
					.findAny().orElse(null);

			if (result == null) {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}

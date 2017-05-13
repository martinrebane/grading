package ee.ttu.kert.maria.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

	private File startFolder;
	private List<File> files;

	public FileReader(String path) {
		startFolder = new File(path);
		files = new ArrayList<>();
	}

	public String getMain() {
		return getMain(startFolder);
	}

	public List<File> getAllFiles() {
		return getAllFiles(startFolder);
	}

	private List<File> getAllFiles(File folder) {
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				files.add(file);
			} else {
				return getAllFiles(file);
			}
		}
		return files;
	}

	private String getMain(File folder) {
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String absolutePath = file.getAbsolutePath();
				if (isMain(absolutePath)) {
					return file.getName();
				}
			} else {
				String finalPath = getMain(file);
				if (finalPath != null) {
					if (folder.equals(startFolder)) {
						String absolutePath = folder.getAbsolutePath();
						String[] pathFolders = absolutePath.split("\\\\");
						String taskFolder = pathFolders[pathFolders.length - 2] + "/";
						return taskFolder + file.getName() + "/" + finalPath;
					}
					return file.getName() + "/" + finalPath;
				}
			}
		}
		return null;
	}

	public String read(File file) {
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

package ee.ttu.kert.maria.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
	
	private File startFolder;

	public FileReader(String path) {
		startFolder = new File(path);
	}
	
	public String getMain() {
		return getMain(startFolder);
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

	private boolean isMain(String path) {
		Path filePath = Paths.get(path);
		String result;
		try {
			List<String> lines = Files.readAllLines(filePath);
			result = lines.stream().filter(s -> s.contains("public static void main(String[] args)")).findAny()
					.orElse(null);

			if (result == null) {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}

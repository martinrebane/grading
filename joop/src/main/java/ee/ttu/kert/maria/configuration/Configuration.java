package ee.ttu.kert.maria.configuration;

public class Configuration {
	
	private static final String REPO_PATH = "D:/Users/mammu/workspace/loputoo/repod/";
	
	private static final String HASH_PATH = "D:/Users/mammu/workspace/loputoo/hashes/";
	
	private static final String ZIP_PATH = "D:/Users/mammu/workspace/loputoo/zips/";
	
	private static final String PLAGIARISM_PATH = "D:/Users/mammu/workspace/loputoo/plagiarism/";
	
	private static final String MOSS_USERID = "567472279";

	public static String getRepoPath() {
		return REPO_PATH;
	}

	public static String getHashPath() {
		return HASH_PATH;
	}

	public static String getZipPath() {
		return ZIP_PATH;
	}

	public static String getPlagiarismPath() {
		return PLAGIARISM_PATH;
	}

	public static String getMossUserid() {
		return MOSS_USERID;
	}
}

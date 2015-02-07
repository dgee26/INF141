import java.io.File;


public class Files {

	public static void parseFiles(File[] files){
		for (File emailDir : files){
			if (emailDir.isDirectory()){
				String s = "" + emailDir.getName() + "/";
				File[] subFiles = emailDir.listFiles();
				for(File subFolder : subFiles){
					s = s + subFolder.getName() + "/";
					if (subFolder.isDirectory()){
						File[] docs = subFolder.listFiles();
						for (File doc : docs){
							String path = s + doc.getName() + ".";
							System.out.println(path);
						}
					}
				}
			}
		}
	}
}

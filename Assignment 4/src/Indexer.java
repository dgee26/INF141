import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Indexer {

	private static TreeMap<String, List<Integer>> positions = new TreeMap<String, List<Integer>>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir").listFiles();
		File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/testfolder").listFiles();
		TreeMap <String, TreeMap<String, Integer>> index = fillIndex(files);
		printIndex(index, positions);
	}
	//Go through doc (Get path)
	//

	public static TreeMap<String, TreeMap<String, Integer>> fillIndex(File[] files){
		TreeMap<String, TreeMap<String, Integer>> index = new TreeMap<String, TreeMap<String, Integer>>();
		for (File emailDir : files){
			if (emailDir.isDirectory()){
				File[] subFiles = emailDir.listFiles();
				for(File subFolder : subFiles){
					if (subFolder.isDirectory()){
						File[] docs = subFolder.listFiles();
						for (File doc : docs){
							String s = doc.toString();
							String s2 = s.replace("\\", "/");
							String path = s2.replace("C:/Users/Dillon/workspace3/enron_mail_20110402/testfolder", "");
							TreeMap<String, Integer> map = WordFrequencyCounter.tokenizeFile(doc);
							positions = WordFrequencyCounter.getWordPositions(doc);
							System.out.println(positions);
							index.put(path, map);
							System.out.println(path);
						}
					}
				}
			}
		}
		System.out.println("end");
		return index;
	}
	
	private static void clearFile(File file){
		file.delete();
	}

	public static void printIndex(Map<String, TreeMap<String, Integer>> index, Map<String, List<Integer>> positions){
		File file = new File("index_plain.txt");
		if (file.exists()){
			clearFile(file);
		}
		for(String p : index.keySet()){
			String path = p;
			TreeMap<String, Integer> words = index.get(p);
			for (String word : words.keySet()){
				int freq = words.get(word);
				List<Integer> list = positions.get(word);
				String listofPositions = "";
				if (list == null){
					listofPositions = "";
				}
				else{
					listofPositions = "";
					for (int i = 0; i<list.size(); i++){
						String s = list.get(i).toString();
						listofPositions = listofPositions + s ;
						int last = list.size()-1;
						if(i != last){
							listofPositions = listofPositions + ", ";
						}
						try{
							Writer pw = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
							pw.write(word + "\t" + path + " : " + freq + " : " + listofPositions + "\r\n");
							pw.close();
						}
						catch (IOException e) {
							System.out.println("IOException");
						}
					}
				}
				
			}

		}

	}
}

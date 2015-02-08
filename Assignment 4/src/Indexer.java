import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Indexer {

	private static TreeMap<String, List<Integer>> positions = new TreeMap<String, List<Integer>>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402").listFiles();
		TreeMap <String, TreeMap<String, Integer>> index = fillIndex(files);
		printIndex(index, positions);
	}
	//Go through doc (Get path)
	//

	public static TreeMap<String, TreeMap<String, Integer>> fillIndex(File[] files){
		TreeMap<String, TreeMap<String, Integer>> index = new TreeMap<String, TreeMap<String, Integer>>();
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
							TreeMap<String, Integer> map = WordFrequencyCounter.tokenizeFile(doc);
							positions = WordFrequencyCounter.getWordPositions(doc);
							index.put(path, map);
							System.out.println(path);
						}
					}
				}
			}
		}
		return index;
	}


	public static void printIndex(Map<String, TreeMap<String, Integer>> index, Map<String, List<Integer>> positions){
		File file = new File("index_plain.dat");
		for(String p : index.keySet()){
			String path = p;
			TreeMap<String, Integer> words = index.get(p);
			for (String word : words.keySet()){
				int freq = words.get(word);
				List<Integer> list = positions.get(word);
				String listofPositions = "";
				for (int i = 0; i<list.size(); i++){
					String s = list.get(i).toString();
					listofPositions = listofPositions + "," + s;
				}
				try{
					PrintWriter pw = new PrintWriter((new FileOutputStream(file, true)));
					pw.println(word + "\t" + path + " : " + freq + " : " + listofPositions );
					pw.close();
				}
				catch (IOException e) {
					System.out.println("IOException");
				}
			}

		}

	}
}

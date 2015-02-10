import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Indexer {

	//public static TreeMap<String,List<Position>> indexResults = new TreeMap<String,List<Position>>();
	public static TreeMap<String,List<Position>> results = new TreeMap<String,List<Position>>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Stars");
		//File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/testFolder").listFiles();
		File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir").listFiles();
		fillIndex(files);
		printIndex(results);
	}
	//Go through doc (Get path)
	//

	public static void fillIndex(File[] files){
		System.out.println("Starting");
		
		//List<TreeMap<String,List<Position>>> results = new ArrayList<TreeMap<String,List<Position>>>();
		for (File emailDir : files){
			if (emailDir.isDirectory()){
				File[] subFiles = emailDir.listFiles();
				for(File subFolder : subFiles){
					if (subFolder.isDirectory()){
						File[] docs = subFolder.listFiles();
						for (File doc : docs){
							String s = doc.toString();
							String s2 = s.replace("\\", "/");
							String path = s2.replace("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir", "");
							TreeMap<String,List<Position>> index = WordFrequencyCounter.tokenizeFile(doc, path);
							mergeMaps(index);
							//putIntoResults(index);
							//printIndex(index);
							System.out.println(path);
						}
					}
				}
			}
		}
		System.out.println("end");
	}

	public static void mergeMaps(TreeMap<String,List<Position>> index){
		for (String s : index.keySet()){
			if (results.containsKey(s)){
				List<Position> lp1 = results.get(s);
				List<Position> lp2 = index.get(s);
				for (int i = 0; i<lp2.size(); i++){
					Position p = lp2.get(i);
					//System.out.println( p.getPath() + p.getFrequency() + p.getPositions());
					lp1.add(lp2.get(i));
				}
			}
			else{
				results.put(s, index.get(s));
			}
		}
		//return results;
	}
	private static void clearFile(File file){
		file.delete();
	}

	public static void printIndex(TreeMap<String,List<Position>> map){
		File file = new File("index_plain.txt");

		for(String word : map.keySet()){
			List<Position> lp = map.get(word);
			for (int i = 0; i<lp.size(); i++){
				Position p = lp.get(i);
				//System.out.println(word + " " + p.getPath() + p.getFrequency() + p.getPositions());
				String s = word + "\t" + p.getPath() + ":" + p.getFrequency() + ":" + p.getPositions();	
				try{
					Writer pw = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
					pw.append(s + "\r\n");
					pw.close();
				}
				catch (IOException e) {
					System.out.println("IOException");
				}
			}
		}
	}
}

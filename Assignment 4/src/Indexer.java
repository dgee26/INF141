import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Indexer {

	private static TreeMap<String, List<Integer>> positions = new TreeMap<String, List<Integer>>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir").listFiles();
		File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/testfolder").listFiles();
		fillIndex(files);
	}
	//Go through doc (Get path)
	//

	public static void fillIndex(File[] files){
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
							String path = s2.replace("C:/Users/Dillon/workspace3/enron_mail_20110402/testfolder", "");
							TreeMap<String,List<Position>> index = WordFrequencyCounter.tokenizeFile(doc, path);
							printIndex(index);
							//results.add(index);
							System.out.println(path);
						}
					}
				}
			}
		}
		System.out.println("end");
		//return results;
	}
	

	private static void clearFile(File file){
		file.delete();
	}

	public static void printIndex(TreeMap<String,List<Position>> map){
		File file = new File("index_plain.txt");
		if (file.exists()){
			clearFile(file);
		}
		
		for(String word : map.keySet()){
			List<Position> lp = map.get(word);
			String s = word + "\t";
			for (int i = 0; i<lp.size(); i++){
				Position p = lp.get(i);
				s = s + p.toString();
				int last = lp.size()-1;
				if(i != last){
					s = s + "\t";
				}
			}
			try{
				Writer pw = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
				pw.write(s + "\r\n");
				pw.close();
			}
			catch (IOException e) {
				System.out.println("IOException");
			}
		}
		
	}
}

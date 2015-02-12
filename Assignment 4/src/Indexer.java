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
		//show();
		printIndex(results);
	}
	//Go through doc (Get path)
	//

	public static void fillIndex(File[] files){
		System.out.println("Starting");
		TreeMap<String,List<Position>> boogie = new TreeMap<String,List<Position>>();
		int user = 0;
		for (File userDir : files){
			user++;
			if(user < 4){
				if (userDir.isDirectory()){
					File[] subFiles = userDir.listFiles();
					for(File subFolder : subFiles){
						if (subFolder.isDirectory()){
							File[] docs = subFolder.listFiles();
							for(File doc :docs){
								if(doc.isDirectory()){
									File[] d = doc.listFiles();
									for (File f : d){
										String t = f.toString();
										String t2 = t.replace("\\", "/");
										String path = t2.replace("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir", "");
										TreeMap<String,Position> temp = WordFrequencyCounter.tokenizeFile(doc, path);
										for(String w : temp.keySet()){
											if (boogie.containsKey(w)){
												List<Position> p = boogie.get(w);
												p.add(temp.get(w));
												boogie.put(w, p);
											}
											else{
												List<Position> lp = new ArrayList<Position>();
												lp.add(temp.get(w));
												boogie.put(w, lp);
											}
											
										}
										System.out.println(path);
									}
								}
								else{
									String s = doc.toString();
									String s2 = s.replace("\\", "/");
									String path = s2.replace("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir", "");
									TreeMap<String,Position> temp = WordFrequencyCounter.tokenizeFile(doc, path);
									for(String w : temp.keySet()){
										if (boogie.containsKey(w)){
											List<Position> p = boogie.get(w);
											p.add(temp.get(w));
											boogie.put(w, p);
										}
										else{
											List<Position> lp = new ArrayList<Position>();
											lp.add(temp.get(w));
											boogie.put(w, lp);
										}
										
									}
									System.out.println(path);
								}
							}
						}
						else{
							String u = userDir.toString();
							String u2 = u.replace("\\", "/");
							String path = u2.replace("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir", "");
							TreeMap<String,Position> temp = WordFrequencyCounter.tokenizeFile(userDir, path);
							for(String w : temp.keySet()){
								if (boogie.containsKey(w)){
									List<Position> p = boogie.get(w);
									p.add(temp.get(w));
									boogie.put(w, p);
								}
								else{
									List<Position> lp = new ArrayList<Position>();
									lp.add(temp.get(w));
									boogie.put(w, lp);
								}
								
							}
							System.out.println(path);
						}
					}
				}
				else{
					String u = userDir.toString();
					String u2 = u.replace("\\", "/");
					String path = u2.replace("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir", "");
					TreeMap<String,Position> temp = WordFrequencyCounter.tokenizeFile(userDir, path);
					for(String w : temp.keySet()){
						if (boogie.containsKey(w)){
							List<Position> p = boogie.get(w);
							p.add(temp.get(w));
							boogie.put(w, p);
						}
						else{
							List<Position> lp = new ArrayList<Position>();
							lp.add(temp.get(w));
							boogie.put(w, lp);
						}
						
					}
					System.out.println(path);
				}
			}
			else{
				mergeMaps(boogie);
				boogie = new TreeMap<String, List<Position>>();
				user = 0;
				System.out.println("merger");
			}
			
		}
		System.out.println("Finished");
		//return boogie;
	}
	
	public static void show(){
		System.out.println(results.get("lime"));
	}

	public static void mergeMaps(TreeMap<String,List<Position>> index){
		for (String s : index.keySet()){
			if(results.containsKey(s)){
				List<Position> lp1 = results.get(s);
				List<Position> lp2 = index.get(s);
				for(int i = 0; i<lp2.size(); i++){
					lp1.add(lp2.get(i));
				}
				results.put(s, lp1);
			}
			else{
				results.put(s, index.get(s));
			}
		}
		//return results;
	}
	
	public static void printIndex(TreeMap<String,List<Position>> map){
		File file = new File("index_plain.txt");
		
		for(String word : map.keySet()){
			try{
				Writer pw = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
				String cleaned = map.get(word).toString().replace("[", "");
				cleaned = cleaned.replace("]", "");
				pw.append(word + "\t" + cleaned + "\r\n");
				pw.close();
			}
			catch (IOException e) {
				System.out.println("IOException");
			}
		}
			
		System.out.println("das all she wrote");
	}
}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {


	
	public WordFrequencyCounter() {}

	// Adapted from http://blog.projectnibble.org/2013/06/21/ways-to-sort-lists-of-objects-in-java-based-on-multiple-fields/
	// Used for TwoGramFrequencyCounter and PalindromeFrequencyCounter
	public static class AlphabeticalOrder implements Comparator<Position>{
		@Override
		public int compare(Position f1, Position f2){
			// Checks order for frequencies
			int n = Integer.valueOf(f2.getFrequency()).compareTo(Integer.valueOf(f1.getFrequency()));

			if (n != 0){
				return n;
			}

			return f1.getPath().compareTo(f2.getPath());	//Compares words for alphabetical order
		}

	}

	public static String[] storeForStemming(String word){
		String [] sa = new String[501];
		return sa;
	}

	//Tokenizes words and count word frequencies
	public static TreeMap<String, Position> tokenizeFile(File input, String path) {
		TreeMap<String,Position> results = new TreeMap<String,Position>();
		
		String line;
		int lineNumber = 1;
		try {
			BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
			while((line = br.readLine()) != null){
				//System.out.println(line);
				lineNumber++;
				String [] sa = line.split("\\W+\\s*|\\s+\\W+|\\_+");
		
				if(notEmailHeader(line)){
					for (int i = 0; i<sa.length; i++){
						sa[i] = sa[i].toLowerCase();		 				
						sa[i] = sa[i].replaceAll("[^a-zA-Z0-9]","");
						String stem = charToStem(sa[i]);
						if (!stem.equals("")&& notStopWord(stem)){
							int freq = 1;
							if (results.containsKey(stem)){
								Position p = results.get(stem);
								p.addPosition(lineNumber);
								p.incrementFrequency();
							}
							else{
								List<Integer> newPos = new ArrayList<Integer>();
								newPos.add(lineNumber);
								Position p = new Position(path, freq , newPos);
								results.put(sa[i], p);
							}
						}	
					}
				}
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	public static String charToStem(String s){
		String newString;
		Stemmer stem = new Stemmer();
		char[] c = s.toCharArray();
		for (int i = 0; i<c.length; i++){
			stem.add(c[i]);
		}
		stem.stem();
		newString = stem.toString();
		return newString;
	}

	public static boolean notStopWord(String s){
		File file = new File("C:/Users/Dillon/workspace3/Assignment 4/src/StopWords.txt");
		try {
			Scanner scan = new Scanner(new FileReader(file));
			while(scan.hasNext()){
				String t = scan.next();
				//System.out.println(s + ":" + t);
				if(t.equals(s)){
					scan.close();
					return false;
				}
			}
			scan.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static boolean notEmailHeader(String s){
		File file = new File("C:/Users/Dillon/workspace3/Assignment 4/src/EmailHeaders.txt");
		try {
			Scanner scan = new Scanner(new FileReader(file));
			while(scan.hasNext()){
				String t = scan.next();
				//System.out.println(s + ":" + t);
				if(s.contains(t)){
					scan.close();
					return false;
				}
			}
			scan.close();  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	public static void main(String[] args) {
		/*File file = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/testFolder/criminals/inbox/1");
		File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/testFolder").listFiles();
		Indexer.fillIndex(files);
		TreeMap<String, List<Position>> map = tokenizeFile(file, "blacksheep");
		Indexer.printIndex(Indexer.results);
		File file1 = new File("index_plain.txt");
		
		Indexer.mergeMaps(map);
		

		for(String word : map.keySet()){
			List<Position> lp = map.get(word);
			for (int i = 0; i<lp.size(); i++){
				Position p = lp.get(i);
				String cleaned = p.getPositions().replace("[", "");
				cleaned = cleaned.replace("]", "");
				System.out.println(word + " " + p.getPath() + p.getFrequency() + p.getPositions());
				String s = word + "\t" + p.getPath() + ":" + p.getFrequency() + ":" + cleaned;	
				try{
					Writer pw = new OutputStreamWriter(new FileOutputStream(file1, true), "UTF-8");
					pw.append(s + "\r\n");
					pw.close();
				}
				catch (IOException e) {
					System.out.println("IOException");
				}
			}
		}*/
		
	} 


}



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

			return f1.getWord().compareTo(f2.getWord());	//Compares words for alphabetical order
		}

	}
	
	public static String[] storeForStemming(String word){
		String [] sa = new String[501];
		return sa;
	}
	
	//Tokenizes words and count word frequencies
	public static TreeMap<String,List<Position>> tokenizeFile(File input, String path) {
		TreeMap<String,List<Position>> results = new TreeMap<String,List<Position>>();
		TreeMap<String, Integer> map =  new TreeMap<String, Integer>();
		//List<String> lf = Stemmer.stemWords(input);
		int lineNumber = 1;
		try {
			Scanner scan = new Scanner(new FileReader(input));  //Creates file scanner
			scan.useDelimiter("\\W+\\s*|\\s+\\W+|\\_+");
			while (scan.hasNext()){
				if (scan.hasNextLine()){
					lineNumber++;
				}	
				String s = scan.next();     					//Gets next string
				s = s.replaceAll("[^a-zA-Z0-9]","");
				s = s.toLowerCase();		 					//converts string to lowercase
				if (!s.equals("") && notStopWord(s)){
					int n = 1;	
					//Store in index
					if (results.containsKey(s)){
						List<Position> lp = results.get(s);
						editingPosition(lp, lineNumber, path);
						results.put(s, lp);
					}
					else{
						List<Integer> newPos = new ArrayList<Integer>();
						newPos.add(lineNumber);
						Position p = new Position(path, n , newPos);
						List<Position> newLp = new ArrayList<Position>();
						newLp.add(p);
						List<Position> lp = new ArrayList<Position>();
						results.put(s, lp);
					}
				}

			}				
		}
		catch (FileNotFoundException e) {
			//Catches error if file cannot be found or does not exist
			e.printStackTrace();				
		}

		return results;
	}
	
	public static void editingPosition(List<Position> lp, int lineNumber, String path){
		if (lp.isEmpty()){
			List<Integer> newPos = new ArrayList<Integer>();
			newPos.add(lineNumber);
			Position p = new Position(path, 1, newPos);
			lp.add(p);
		}
		else{
			for (int i = 0; i<lp.size(); i++){
				Position p = lp.get(i);
				if(path.equals(p.getWord())){
					p.incrementFrequency();
					p.addPosition(lineNumber);
				}
				
			}	
			AlphabeticalOrder ordered = new AlphabeticalOrder();
			Collections.sort(lp, ordered);
		}
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

	public static void main(String[] args){
		/*File file = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/testfolder/criminals/inbox/1");
		TreeMap<String, List<Integer>> tm = getWordPositions(file);*/
		
	}

}


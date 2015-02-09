import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
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
	public static class AlphabeticalOrder implements Comparator<Frequency>{
		@Override
		public int compare(Frequency f1, Frequency f2){
			// Checks order for frequencies
			int n = Integer.valueOf(f2.getFrequency()).compareTo(Integer.valueOf(f1.getFrequency()));

			if (n != 0){
				return n;
			}

			return f1.getText().compareTo(f2.getText());	//Compares words for alphabetical order
		}

	}
	
	public static String[] storeForStemming(String word){
		String [] sa = new String[501];
		return sa;
	}
	
	//Does counting frequency too
	public static TreeMap<String, Integer> tokenizeFile(File input) {
		TreeMap<String, Integer> map =  new TreeMap<String, Integer>();
		List<String> lf = Stemmer.stemWords(input);
		try {
			Scanner scan = new Scanner(new FileReader(input));  //Creates file scanner
			scan.useDelimiter("\\W+\\s*|\\s+\\W+|\\_+");
			while (scan.hasNext()){
				String s = scan.next();     					//Gets next string
				s = s.replaceAll("[^a-zA-Z0-9]","");
				s = s.toLowerCase();		 					//converts string to lowercase
				if (!s.equals("") && notStopWord(s)){
					int n = 1;	
					//Store in index
					if (map.containsKey(s)){
						int frequency = map.get(s);
						map.put(s, frequency + 1);
					}
					else{
						map.put(s, n);
					}		
				}

			}
			scan.close();

		}
		catch (FileNotFoundException e) {
			//Catches error if file cannot be found or does not exist
			e.printStackTrace();				
		}

		return map;
	}

	public static TreeMap<String, List<Integer>> getWordPositions(File input) {
		TreeMap<String, List<Integer>> maps = new TreeMap<String, List<Integer>>();
		int lineNumber = 1;			
		System.out.println(lineNumber);
		try {
			Scanner scan = new Scanner(new FileReader(input));  //Creates file scanner
			while (scan.hasNext()){
				if (scan.hasNextLine()){
					lineNumber++;
				}	
				String s = scan.next();     					//Gets next string
				s = s.replaceAll("[^a-zA-Z0-9]","");
				s = s.toLowerCase();		 					//converts string to lowercase
				if (!s.equals("") && notStopWord(s)){
					if(maps.containsKey(s)){
						List<Integer> positions = maps.get(s);
						positions.add(lineNumber);
						maps.put(s, positions);
					}
					else{
						List<Integer> positions = new ArrayList<Integer>(lineNumber);
						maps.put(s, positions);
					}

				}

			}
			scan.close();

		}
		catch (FileNotFoundException e) {
			//Catches error if file cannot be found or does not exist
			e.printStackTrace();				
		}
		return maps;
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


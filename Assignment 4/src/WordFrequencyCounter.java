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
	 //Does counting frequency too
	 public static List<Frequency> tokenizeFile(File input) {
			//ArrayList<String> a = new ArrayList<String>();
		    List<Frequency> list = new ArrayList<Frequency>();
		 	TreeMap<String, Integer> map =  new TreeMap<String, Integer>();
			try {
				Scanner scan = new Scanner(new FileReader(input));  //Creates file scanner
				while (scan.hasNext()){
					String s = scan.next();     					//Gets next string
					s = s.replaceAll("[^a-zA-Z0-9]","");
					s = s.toLowerCase();		 					//converts string to lowercase
					if (!s.equals("") && isStopWord(s)){
						int n = 1;	
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

			for (String word: map.keySet()){
				Frequency f = new Frequency(word, map.get(word));
				list.add(f);
			}
			AlphabeticalOrder ordered = new AlphabeticalOrder();
			Collections.sort(list, ordered);						//Sorts alphabetically
			
			return list;
		}
	 
	 public static boolean isStopWord(String s){
			File file = new File("StopWords.txt");
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
}


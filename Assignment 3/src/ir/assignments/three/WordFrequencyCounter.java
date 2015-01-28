package ir.assignments.three;

import ir.assignments.three.Frequency;
import ir.assignments.three.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {
	
	public WordFrequencyCounter() {}
	
	public static List<Frequency> computeWordFrequencies(List<String> words) {
		// TODO Write body!	
		List<Frequency> list = new ArrayList<Frequency>();
		// Checks if list is empty and if it is, returns empty list
		if (words == null){
			return list;
		}
		//Goes through List<String>
		for (int i = 0; i<words.size(); i++){
			int n = 1;								// Frequency number default is 1
			String s = words.get(i); 				//Gets word from list
			//Not include main domain in results
			//if (!s.equals("http://www.ics.uci.edu")){
				Frequency f = new Frequency(s,n);      	//Creates new entry 
				boolean b = existsInList(list, s);
				// If word does not already exist in list, then add to list
				if (!b){
					list.add(f);						// Add to arraylist
				}
			//}
		}
		
		AlphabeticalOrder ordered = new AlphabeticalOrder();
		Collections.sort(list, ordered);						//Sorts alphabetically
		return list;											//returns list of words
	}
	
	// Method that checks for duplicate words and then increase the frequency of that word by 1
	// Will return true if duplicate is found
	public static boolean existsInList(List<Frequency> lF, String s){
		Iterator<Frequency> iterate = lF.iterator();      
		while(iterate.hasNext()){
			Frequency fr = iterate.next();
			// Compares word from words list and word from list of words/frequencies
			// Compares only the words
			if (s.equals(fr.getText())){
				fr.incrementFrequency();        //Increments the frequency of existing word 
				return true;
			}
		}
		return false;
	}
	
	
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

	/**
	 * Runs the word frequency counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	 
	public static void main(String[] args) {
		File file = new File("C:/Users/Dillon/workspace3/Assignment 3/Subdomains2.txt");
		List<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeWordFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}


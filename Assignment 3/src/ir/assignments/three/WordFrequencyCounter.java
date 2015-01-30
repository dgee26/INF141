package ir.assignments.three;

import ir.assignments.three.Frequency;
import ir.assignments.three.Utilities;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {
	
	public WordFrequencyCounter() {}
	
	public static List<Frequency> computeWordFrequencies(List<String> words) {
		// TODO Write body!	
		List<Frequency> list = new ArrayList<Frequency>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Checks if list is empty and if it is, returns empty list
		if (words == null){
			return list;
		}
		//Goes through List<String>
		for (int i = 0; i<words.size(); i++){
			int n = 1;	
			String s = words.get(i);// Frequency number default is 1
			if (map.containsKey(s)){
				int frequency = map.get(s);
				map.put(s, frequency + 1);
			}
			else{
				map.put(s, n);
			}
		}
		
		for (String word: map.keySet()){
			Frequency f = new Frequency(word, map.get(word));
			list.add(f);
		}
		AlphabeticalOrder ordered = new AlphabeticalOrder();
		Collections.sort(list, ordered);						//Sorts alphabetically
		return list;											//returns list of words
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
		/*List<String> test = new ArrayList<String>();
		String s = " jfdjdf fsdg sdfgkfdg asg asdg asdg asdg asdg asdg !!! AFE%#^ @$adAD";
		test.add(s);
		System.out.println(test);
		Ranking.topWords(test);*/
	}
}


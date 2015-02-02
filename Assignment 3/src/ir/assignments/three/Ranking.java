package ir.assignments.three;

import ir.assignments.three.WordFrequencyCounter.AlphabeticalOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Ranking {
	
	//Counts the body of the page and then returns the word count
	public static int countText(String text) {
		//Splits by a regex that handles some parsing errors we were getting
		String [] sa = text.split("\\W+\\s*|\\s+\\W+|\\_+");
		int wordCounter = 0;
		//Goes through each parsed word, cleans it, and then increments count
		for (int i = 0; i<sa.length; i++){
			sa[i] = sa[i].toLowerCase();		 				
			sa[i] = sa[i].replaceAll("[^a-zA-Z0-9]","");
			if (!sa[i].equals("")){
				wordCounter++;	
			}	
		}
		return wordCounter;
	}
	
	//Tokenizer for page text and then returns list of words
	// Same as countText but filters out stop words
	public static List<String> tokenizeWords(String text) {
		List<String> list = new ArrayList<String>();
		//Splits by a regex that handles some parsing errors we were getting
		String [] sa = text.split("\\W+\\s*|\\s+\\W+|\\_+");
		
		//Goes through each word, cleans it, and then if it is not a stop word, adds it to the list
		for (int i = 0; i<sa.length; i++){
			sa[i] = sa[i].toLowerCase();		 				
			sa[i] = sa[i].replaceAll("[^a-zA-Z0-9]","");
			if (!sa[i].equals("") && compareCommonWords(sa[i])){
				list.add(sa[i]);
			}	
		}
		//System.out.println(list);
		return list;
	}
	
	public static String longestPage(HashMap<String, Integer> map) {
		//HashMap<String, Integer> map = new HashMap<String, Integer>();
		List<Frequency> list = new ArrayList<Frequency>();
		List<Frequency> results = new ArrayList<Frequency>();
		
		// Grabs info from HashMap to List<Frequency>
		for (String u: map.keySet()){
			Frequency notReally = new Frequency (u,map.get(u));
			list.add(notReally);
		}
		
		//Sorts List<Frequency> in alphabetical order and numerical order in desc order
		AlphabeticalOrder ordered = new AlphabeticalOrder();
		Collections.sort(list, ordered);			
		
		Frequency f = list.get(0);					//Grabs first in the list 
		int topValue = f.getFrequency();			//Gets the word count of that url
		String s = "";

		// Goes through list with same word counts and then concats the urls together
		// Will stop after top frequency urls are done
		for (int i = 0; i<list.size(); i++){
			Frequency temp = list.get(i);
			int tempValue = temp.getFrequency();
			if (topValue == tempValue){
				results.add(temp);
				s = temp.getText() + ", ";
			}
			else{
				return s;
			}
		}
		return s;	
	}
	
	//Calculates word frequency and returns list
	// Uses hash map
	public static List<Frequency> topWords(List<String> text){
		List<Frequency> list = new ArrayList<Frequency>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Checks if list is empty and if it is, returns empty list
		if (text == null){
			return list;
		}
		
		for (int i = 0; i<text.size(); i++){
			String s = text.get(i);
			List<String> words = tokenizeWords(s);
			
			for (int j = 0; j<words.size(); j++){
				int n = 1;	
				String t = words.get(j);
				//Checks if word already found
				if (map.containsKey(t)){
					int frequency = map.get(t);
					map.put(t, frequency + 1);
				}
				else{
					map.put(t, n);
				}
			}
			
		}
		// Puts values into a List<Frequency>
		for (String word: map.keySet()){
			Frequency f = new Frequency(word, map.get(word));
			list.add(f);
		}
		AlphabeticalOrder ordered = new AlphabeticalOrder();
		Collections.sort(list, ordered);						//Sorts alphabetically
		return list;											//returns list of words
	}
	
	// Checks for stop words returns true if not a stop word and false for a stop word
	public static boolean compareCommonWords(String s){
		//Reads from file with a list ofStop words
		File file = new File("StopWords.txt");
		try {
			Scanner scan = new Scanner(new FileReader(file));
			while(scan.hasNext()){
				String t = scan.next();
				//System.out.println(s + ":" + t);
				if(t.equals(s)){
					return false;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}

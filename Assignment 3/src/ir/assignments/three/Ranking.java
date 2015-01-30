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
	
	//***********SEE IF CAN OPTIMIZE ANYTHING***************************
	
	public static int countText(String text) {
		String [] sa = text.split("\\W+\\s*|\\s+\\W+|\\_+");
		int wordCounter = 0;
		
		for (int i = 0; i<sa.length; i++){
			sa[i] = sa[i].toLowerCase();		 				
			sa[i] = sa[i].replaceAll("[^a-zA-Z0-9]","");
			if (!sa[i].equals("")){
				wordCounter++;	
			}	
		}
		return wordCounter;
	}
	
	public static List<String> tokenizeWords(String text) {
		List<String> list = new ArrayList<String>();
		String [] sa = text.split("\\W+\\s*|\\s+\\W+|\\_+");
		
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
		
		for (String u: map.keySet()){
			Frequency notReally = new Frequency (u,map.get(u));
			list.add(notReally);
		}
		
		AlphabeticalOrder ordered = new AlphabeticalOrder();
		Collections.sort(list, ordered);						//Sorts alphabetically
		
		Frequency f = list.get(0);
		int topValue = f.getFrequency();
		String s = "";
		
		for (int i = 0; i<list.size(); i++){
			Frequency temp = list.get(i);
			int tempValue = temp.getFrequency();
			if (topValue == tempValue){
				results.add(temp);
				s = temp.getText() + ", ";
			}
		}
		return s;				
		
	}
	
	public static List<Frequency> topWords(List<String> text){
		List<Frequency> list = new ArrayList<Frequency>();
		//List<String> words = new ArrayList<String>();
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
				if (map.containsKey(t)){
					int frequency = map.get(t);
					map.put(t, frequency + 1);
				}
				else{
					map.put(t, n);
				}
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

	public static boolean compareCommonWords(String s){
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

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
	
	public static int countText(String text) {
		String [] sa = text.split(" ");
		int wordCounter = 0;
		
		for (int i = 0; i<sa.length; i++){
			sa[i] = sa[i].toLowerCase();		 				//converts string to lowercase
			sa[i] = sa[i].replaceAll("[^a-zA-Z0-9]","");
			if (!sa[i].equals("")){
				wordCounter++;	
			}	
		}
		return wordCounter;
	}
	
	public static List<String> cleanWords(List<String> text) {
		List<String> list = new ArrayList<String>();
		
		for (int i = 0; i<text.size(); i++){
			String s = text.get(i);
			s = s.toLowerCase();		 				//converts string to lowercase
			s = s.replaceAll("[^a-zA-Z0-9]","");
			if (!s.equals("")){
				list.add(s);
			}	
		}
		return list;
	}
	
	public static String longestPage(HashMap<String, Integer> map) {
		//HashMap<String, Integer> map = new HashMap<String, Integer>();
		List<Frequency> list = new ArrayList<Frequency>();
		
		/*if (map.containsKey(url)){
			int count = map.get(url);
			if(textCount > count){
				map.put(url, textCount);
			}
		}
		else{
			map.put(url, textCount);
		}*/
		
		for (String u: map.keySet()){
			Frequency notReally = new Frequency (u,map.get(u));
			list.add(notReally);
		}
		
		AlphabeticalOrder ordered = new AlphabeticalOrder();
		Collections.sort(list, ordered);						//Sorts alphabetically
		
		Frequency f = list.get(0);
		String longestPage = f.getText();
		return longestPage + "is the longest page in the domain";				
		
	}
	
	public static List<Frequency> topWords(List<String> text){
		List<Frequency> list = new ArrayList<Frequency>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Checks if list is empty and if it is, returns empty list
		List<String> words = cleanWords(text);
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

	public static boolean compareCommonWords(String s){
		File file = new File("StopWords.txt");
		try {
			Scanner scan = new Scanner(new FileReader(file));
			while(scan.hasNext()){
				String t = scan.next();
				System.out.println(s + ":" + t);
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

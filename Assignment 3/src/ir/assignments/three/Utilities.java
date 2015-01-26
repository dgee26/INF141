package ir.assignments.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
	
	public static ArrayList<String> tokenizeFile(File input) {
		// TODO Write body!
		// Need to add worst case error catches
		ArrayList<String> a = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(new FileReader(input));  //Creates file scanner
			//Iterate through text file
			while (scan.hasNext()){
				String s = scan.next();     					//Gets next string
				s = s.toLowerCase();		 					//converts string to lowercase
				s = s.replaceAll("[^a-zA-Z0-9]","");			// Cleans string of non-letters
				// If a blank character is found because of the replaceAll method
				// then do not add blank character
				if (!s.equals("")){
					a.add(s); 									//Add cleaned word to arraylist
				}
				
			}
			scan.close();
		}
		catch (FileNotFoundException e) {
			//Catches error if file cannot be found or does not exist
	        e.printStackTrace();				
	    }
		
		return a;
	}
	

	public static void printFrequencies(List<Frequency> frequencies) {
		// TODO Write body!
		int total = 0;
		int unique = 0;
		String s = "";
		int n = 0;
		// Calculates total count and unique count
		for (int i = 0; i<frequencies.size(); i++){
			Frequency f = frequencies.get(i);		//Gets Frequency entry
			int num = f.getFrequency();				//Gets that entry's frequency number
			unique++; 								//Increments unique total #
			total += num;							//Adds to running total # of words
		}
		Frequency fr = new Frequency(s, n);
		// Checks to see if the list of frequencies is not empty
		// Will get first value of the list and is used later to determine a two gram list or word list
		if (frequencies.size() > 0){		
			fr = frequencies.get(0);
		}
		
		String t = fr.getText();
		//Checks to see if there is a space inside words indicating two or more words stored in a Frequency entry
		// If there is multiple words, print 2-gram. If not, item
		if (t.contains(" ")){
			System.out.println("Total 2-gram count:" + " " + total);
			System.out.println("Unique 2-gram count:" + " " + unique);
			System.out.println();
		}
		else{
			System.out.println("Total item count:" + " " + total);
			System.out.println("Unique item count:" + " " + unique);
			System.out.println();
		}
		
		// Gets word and count stored in array
		for (int i = 0; i<frequencies.size(); i++){
			Frequency f = frequencies.get(i);
			System.out.println(f.getText() + " " + f.getFrequency());
		}
		
		
	}
}
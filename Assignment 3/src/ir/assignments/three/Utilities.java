package ir.assignments.three;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		//HashMap hm = new HashMap();
		try {
			Scanner scan = new Scanner(new FileReader(input));  //Creates file scanner
			//Iterate through text file
			while (scan.hasNext()){
				String s = scan.next();     					//Gets next string
				s = s.toLowerCase();		 					//converts string to lowercase
				// If a blank character is found because of the replaceAll method
				// then do not add blank character
				if (!s.equals("")){
					System.out.println(s);
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
	
	
	public static void clear (){
		File file = new File("Subdomains.txt");
		file.delete();
	}
	
	public static int countUrl(List<String> url) throws IOException{
		System.out.println("Entering");
		int unique = 0;
		
		for (int i = 0; i<url.size(); i++){
			unique++; 								//Increments unique total #

		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("answers.txt", true)));
		pw.println("Unique url count:" + " " + unique);
		pw.close();
		System.out.println("Unique url count:" + " " + unique);
		System.out.println();
		
		return unique;
	}
	
	public static void printFrequencies(List<Frequency> frequencies) {
		// TODO Write body!
		//int total = 0;
		
		clear();
		
		// Gets word and count stored in array
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Subdomains.txt", true)));
			for (int i = 0; i<frequencies.size(); i++){
				Frequency f = frequencies.get(i);
				String s = f.getText();
				int n = f.getFrequency();
				pw.println(s + ", " + n);
			}
            pw.close();
        }catch (IOException e) {
        	System.out.println("IOException");
        }
		
		
	}
	
	public static void print500(List<Frequency> frequencies) {
		// TODO Write body!
		//int total = 0;
		System.out.println("Entering");
		int unique = 0;

		for (int i = 0; i<frequencies.size(); i++){
			unique++; 								//Increments unique total #
		}
		
			System.out.println("Unique url count:" + " " + unique);
			System.out.println();
		//}
		clear();
		
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("CommonWords.txt", true)));
			for (int i = 0; i<frequencies.size(); i++){
				Frequency f = frequencies.get(i);
				String s = f.getText();
				int n = f.getFrequency();
				pw.println(s + " " + n);
				if (i >= 500){
					break;
				}
			}
            pw.close();
        }catch (IOException e) {
        	System.out.println("IOException");
        }

		
		
		
		
	}
}
package ir.assignments.three;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

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
					a.add(s); 								
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
	
	// Utility function to log all answers to answers.txt
	public static void logAnswers(String answers){
		File file = answersLogFile();
		// Gets word and count stored in array
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			pw.println(answers);
			pw.close();
		}
		catch (IOException e) {
			System.out.println("IOException");
		}
	}
	
	// All subdomains and their unique URL count will be written
	// to Subdomains.txt file.
	public static File subdomainLogFile(){
		File file = new File("Subdomains.txt");
		return file;
	}
	public static void clearSubdomain (){
		File file = new File("Subdomains.txt");
		file.delete();
	}
	// Open and setup the answers.txt.
	// we will collect all answers into this file.
	public static File answersLogFile(){
		File file = new File("answers.txt");
		return file;
	}
	// Initialize the answers.txt
	public static void clearAnswersLog(){
		File file = new File("answers.txt");
		file.delete();
	}
	public static void clearCommonWords (){
		File file = new File("CommonWords.txt");
		file.delete();
	}
	
	public static int countUrl(List<String> url){
		int unique = 0;
		for (int i = 0; i<url.size(); i++){
			unique++; 								//Increments unique total #
		}
		return unique;
	}
	
	public static void printFrequencies(List<Frequency> frequencies) {
		clearSubdomain();
		File file = subdomainLogFile();
		// Gets word and count stored in array
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			System.out.println("3.) Unique URLs by Subdomain");
			for (int i = 0; i<frequencies.size(); i++){
				Frequency f = frequencies.get(i);
				String s = f.getText();
				int n = f.getFrequency();
				System.out.println(s + ", " + n);
				pw.println(s + ", " + n);
			}
            pw.close();
        }catch (IOException e) {
        	System.out.println("IOException");
        }
		
		
	}
	
	public static void print500(List<Frequency> frequencies) {
		// TODO Write body!
		int rank = 0;
		int unique = 0;

		for (int i = 0; i<frequencies.size(); i++){
			unique++; 								//Increments unique total #
		}
		
			System.out.println("5.) Unique word count:" + " " + unique);
			System.out.println();
		
		clearCommonWords();
		
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("CommonWords.txt", true)));
			for (int i = 0; i<frequencies.size(); i++){
				rank++;
				Frequency f = frequencies.get(i);
				String s = f.getText();
				int n = f.getFrequency();
				if (i >= 500){
					break;
				}
				System.out.println("#" + rank + " " + s + " " + n);
				pw.println(rank + ".) " + s + " " + n);
				
			}
            pw.close();
        }catch (IOException e) {
        	System.out.println("IOException");
        }
 
	}
}

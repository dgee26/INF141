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
	
	// Clears subdomain file
	public static void clearSubdomain (){
		File file = new File("Subdomains.txt");
		file.delete();
	}
	
	//Clears list of ranked common words file
	public static void clearCommonWords (){
		File file = new File("CommonWords.txt");
		file.delete();
	}
	
	//Counts the unique urls
	public static int countUrl(List<String> url){
		int unique = 0;
		for (int i = 0; i<url.size(); i++){
			unique++; 								//Increments unique total #
		}
		return unique;
	}
	
	//Prints unique frequencies to Subdomains.txt in formatted form
	public static void printFrequencies(List<Frequency> frequencies) {
		clearSubdomain();
		File file = new File("C:/Users/Dillon/workspace3/Assignment 3/Subdomains.txt");
		// Gets word and count stored in array
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			System.out.println("3.) ");
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
	
	//Prints ranking of top 500 words
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
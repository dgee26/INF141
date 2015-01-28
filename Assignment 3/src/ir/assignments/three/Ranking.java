package ir.assignments.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ranking {
	
	public static List<Frequency>computeRank(String s){
		List<String> list = new ArrayList<String>();
		
		return null;
	}
	
	public static boolean IsStopWord(String s){
		//Make sure to change file path
		File file = new File ("C:/Users/Dillon/workspace3/Assignment 3/StopWords.txt");
		try {
			Scanner scan = new Scanner(new FileReader(file));
			while(scan.hasNext()){
				if (scan.next().equals(s)){
					return false;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("test.txt");
		int n = Utilities.countWords(file);
		System.out.println(n);
	}

}

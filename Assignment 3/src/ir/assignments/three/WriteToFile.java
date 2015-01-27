package ir.assignments.three;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToFile {
	
	private static File file = new File("C:/Users/Dillon/workspace3/Assignment 3/Subdomains2.txt");
	
	
	public WriteToFile(){}

	public static void printTo (String s) throws IOException {
		
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        pw.printf("%s" + "%n", s);
        pw.close();
		//private BufferedWriter bw = new BufferedWriter(fw);
		//FileWriter fw = new FileWriter(file);
		//bw.write(s);
	}

}

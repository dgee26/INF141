package ir.assignments.three;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToFile {
	
	public WriteToFile(){}

	public static void printTo (String s) throws IOException {
		File file = new File("C:/Users/Dillon/workspace3/Assignment 3/Subdomains2.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        pw.printf("%s" + "%n", s);
        pw.close();
	}

}

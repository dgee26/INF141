import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Indexer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File[] files = new File("C:/Users/Dillon/workspace3/Assignment 4/enron_mail_20110402").listFiles();
		
	}
	
	public static Map<String, List<Frequency>> fillIndex(File[] files){
		TreeMap<String, List<Frequency>> index = new TreeMap<String, List<Frequency>>();
		
		for (File file : files){
			if (file.isDirectory()){
				File[] fs = file.listFiles();
				for(File f : fs){
					List<Frequency> lf = WordFrequencyCounter.tokenizeFile(f);
					String path = f.getAbsolutePath();
					//Find position of word in doc?
					index.put(path, lf);

				}
			}
		}
		return index;
	}

	
	public void printIndex(Map<String, List<Frequency>> index){
		File file = new File("index_plain.txt");
		
		for(String temp : index.keySet()){
			String path = temp;
			List<Frequency> lf = index.get(temp);
			for (int i = 0; i<lf.size(); i++){
				Frequency f = lf.get(i);
				String word = f.getText();
				int freq = f.getFrequency();
				try{
					PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
					pw.println(word + "\t" + path + " : " + freq + " : " + "position" );
					pw.close();
				}
				catch (IOException e) {
					System.out.println("IOException");
				}
			}
			
		}
	}
}

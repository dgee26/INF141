import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class PartOne {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File[] files = new File("C:/Users/Dillon/workspace3/enron_mail_20110402/maildir").listFiles();
		count(files);
		countTypes(files);
		printRanking(rank(files));
	}
	
	public static void count(File[] files){
		int f = 0;
		int people = 0;
		for (File emailDir : files){
			if (emailDir.isDirectory()){
				people++;
				File[] subFiles = emailDir.listFiles();
				for(File subFolder : subFiles){
					if (subFolder.isDirectory()){
						File[] docs = subFolder.listFiles();
						for (File doc : docs){
							if(doc.isDirectory()){
								File[]d = doc.listFiles();
								for (File fi : d){
									f++;
								}
							}
							else{
								f++;
							}
						}
					}
					else{
						f++;
					}
				}
			}
			else{
				f++;
			}
		}
		System.out.println("Number of people = " + people);
		System.out.println("Number of files = " + f);
	}
	
	public static void countTypes(File[] files){
		int sent = 0;
		int inbox = 0;
		for (File emailDir : files){
			if (emailDir.isDirectory()){
				File[] subFiles = emailDir.listFiles();
				for(File subFolder : subFiles){
					if (subFolder.getName().contains("sent") && subFolder.isDirectory()){
						File[] docs = subFolder.listFiles();
						for (File doc : docs){
							sent++;
						}
					}
					
					if(subFolder.getName().contains("inbox") && subFolder.isDirectory()){
						File[] docs = subFolder.listFiles();
						for (File doc : docs){
							System.out.println(doc.getAbsolutePath());
							inbox++;
						}
					}
				}
			}
		}
		//Categorized by messages in folders labeled sent of inbox
		System.out.println("Number of sent emails = " + sent);
		System.out.println("Number of inbox emails = " + inbox);
	}
	
	public static List<Frequency> rank (File[] files){
		List<Frequency> list = new ArrayList<Frequency>();
		for (File emailDir : files){
			if (emailDir.isDirectory()){
				String person = emailDir.getName();
				int i = 0;
				File[] subFiles = emailDir.listFiles();
				for(File subFolder : subFiles){
					if (subFolder.isDirectory()){
						File[] docs = subFolder.listFiles();
						for (File doc : docs){
							i++;
						}
					}
				}
				Frequency f = new Frequency(person, i);
				list.add(f);
				System.out.println(list);
			}
		}
		
		AlphabeticalOrder ordered = new AlphabeticalOrder();
		Collections.sort(list, ordered);	
		return list;
	}
	
	public static void printRanking (List<Frequency> lf){
		int n = 0;
		for (int i = 0; i<lf.size(); i++){
			n++;
			if(n<11){
				Frequency f = lf.get(i);
				System.out.println(n + ".) " + f.getText() + " : " + f.getFrequency());
			}
		}
	}
	
	public static class AlphabeticalOrder implements Comparator<Frequency>{
        @Override
        public int compare(Frequency f1, Frequency f2){
       	 // Checks order for frequencies
   		 int n = Integer.valueOf(f2.getFrequency()).compareTo(Integer.valueOf(f1.getFrequency()));
   		 
   		 if (n != 0){
   			 return n;
   		 }

   		 return f1.getText().compareTo(f2.getText());	//Compares words for alphabetical order
        }

    }
}

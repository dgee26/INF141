package ir.assignments.three;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Timer;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
	
	public static long totalTime = 0;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		 String crawlStorageFolder = "C:/Users/Dillon/workspace/Assignment 3/crawldata";
         int numberOfCrawlers = 10;

         CrawlConfig config = new CrawlConfig();
         config.setCrawlStorageFolder(crawlStorageFolder);
         config.setUserAgentString("UCI Inf131-CS121 crawler 61996254");
         config.setPolitenessDelay(300);
         /*
          * Instantiate the controller for this crawl.
          */
         PageFetcher pageFetcher = new PageFetcher(config);
         RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
         RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		
		// For the 300ms between page requests
		


         /*
          * For each crawl, you need to add some seed urls. These are the first
          * URLs that are fetched and then the crawler starts following links
          * which are found in these pages
          */
         
         controller.addSeed("http://www.ics.uci.edu/");

         /*
          * Start the crawl. This is a blocking operation, meaning that your code
          * will reach the line after this only when crawling is finished.
          */
         long start = System.currentTimeMillis()/1000;
         controller.start(MyCrawler.class, numberOfCrawlers);  
         long end = System.currentTimeMillis()/1000;
         totalTime = end - start;
         
         //Implement answers
         
         //Answer for question #1
         //Still needs to print to answers.txt
         try{
 			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("answers.txt", true)));
 			pw.println(totalTime);
            pw.close();
         }catch (IOException e) {
         	System.out.println("IOException");
         }
         
         //Answer for question #2
         //Stil needs to print to answers.txt
         File file = new File("C:/Users/Dillon/workspace3/Assignment 3/Subdomains2.txt");
         List<String> words = Utilities.tokenizeFile(file);
         Utilities.countUrl(words);
 		 
 		 //Answer for question #3
 		 List<Frequency> frequencies = WordFrequencyCounter.computeWordFrequencies(words);
 		 Utilities.printFrequencies(frequencies);
 		 
 		 //Answer for question #4
 		 Ranking.longestPage(Crawler.textMap);
         
 		 //Answer for questions #5
 		 List<Frequency> lf = Ranking.topWords(Crawler.textPages);
 		 Utilities.print500(lf);
	}

}

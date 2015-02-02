package ir.assignments.three;

import java.util.List;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
	
	public static long totalTime = 0;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		 //Creates storage folder and intializes number of crawlers
		 String crawlStorageFolder = "C:/Users/Dillon/workspace/Assignment 3/crawldata";
         int numberOfCrawlers = 20;

         CrawlConfig config = new CrawlConfig();
         config.setCrawlStorageFolder(crawlStorageFolder);
         config.setUserAgentString("UCI Inf131-CS121 crawler 61996254");	//User agent
         config.setPolitenessDelay(300);				//For politeness delay
         
         //Instantiates the controller
         PageFetcher pageFetcher = new PageFetcher(config);
         RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
         RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		 CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		 
		 //Add seed for ics.uci.edu domains
         controller.addSeed("http://www.ics.uci.edu/");
         
         long start = System.currentTimeMillis()/1000;		 //Start timer 
         controller.start(Crawler.class, numberOfCrawlers);  //Starts crawler
         long end = System.currentTimeMillis()/1000;		 //End timer
         totalTime = end - start;							 //Calculates time
         
         //Implement answers
         
         //Answer for question #1
         //Still needs to print to answers.txt?
 		 System.out.println("1.) " + totalTime + " seconds");

         //Answer for question #2
         //Stinil needs to print to answers.txt
 		 int urlCount = Utilities.countUrl(Crawler.urlList);
 		 System.out.println("2.) Unique url count = " + urlCount);
 		 
 		 //Answer for question #3
 		 List<Frequency> frequencies = WordFrequencyCounter.computeWordFrequencies(Crawler.urlList);
 		 Utilities.printFrequencies(frequencies);
 		 
 		 //Answer for question #4
 		 System.out.println("4.) " + Ranking.longestPage(Crawler.textMap) + "is the longest page in the domain");
         
 		 //Answer for questions #5
 		 List<Frequency> lf = Ranking.topWords(Crawler.textPages);
 		 Utilities.print500(lf);
	}

}

package ir.assignments.three;

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
	}

}

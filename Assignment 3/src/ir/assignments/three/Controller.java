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
		 String crawlStorageFolder = "crawldata";
         int numberOfCrawlers = 20;

         CrawlConfig config = new CrawlConfig();
         config.setCrawlStorageFolder(crawlStorageFolder);
         config.setUserAgentString("UCI Inf131-CS121 crawler 61996254 72557866");
         // For the 300ms between page requests
         config.setPolitenessDelay(300);
         config.setMaxDepthOfCrawling(-1);
         config.setMaxPagesToFetch(-1);
         config.setResumableCrawling(false);
         /*
          * Instantiate the controller for this crawl.
          */
         PageFetcher pageFetcher = new PageFetcher(config);
         RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
         RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		
		
         /*
          * For each crawl, you need to add some seed urls. These are the first
          * URLs that are fetched and then the crawler starts following links
          * which are found in these pages
          */
         
         controller.addSeed("http://www.ics.uci.edu/");
         // Define here the root domain for which we need to look for the
         // subdomains
         Crawler.setSearchDomain("ics.uci.edu");

         /*
          * Start the crawl. This is a blocking operation, meaning that your code
          * will reach the line after this only when crawling is finished.
          */
         long start = System.currentTimeMillis()/1000;
         controller.start(Crawler.class, numberOfCrawlers);  
         long end = System.currentTimeMillis()/1000;
         totalTime = end - start;
         
         //Implement answers
         /**
          * #1. How much time did it take to crawl the entire domain?
          * #2. How many unique pages did you find in the entire domain? 
          * (Uniqueness is established by the URL, not the page's content.)
          * #3. How many sudomains did you find? Submit the list of subdomains 
          * ordered alphabetically and the number of unique pages detected 
          * in each subdomain. The file should be called Subdomains.txt, 
          * and its content should be lines containing the URL, a comma, 
          * a space, and the number.
          * #4. What is the longest page in terms of number of words? 
          * (Don't count HTML markup as words.)
          * #5. What are the 500 most common words in this domain? 
          * (Ignore English stop words, which can be found, for example, at http://www.ranks.nl/stopwords.) Submit the list of common words ordered by frequency (and alphabetically for words with the same frequency) in a file called CommonWords.txt.
          */
         //Answer for question #1
         // Print the results to answers.txt
 		 System.out.println("1.) " + totalTime + " seconds");
 		 Utilities.clearAnswersLog(); // Initialize the answers.txt
 		 Utilities.logAnswers("#1. Total Crawl Time: " + totalTime + " seconds");
         //Answer for question #2
         
 		 int urlCount = Utilities.countUrl(Crawler.getUniqueUrls());
 		 System.out.println("2.) Unique url count = " + urlCount);
 		 Utilities.logAnswers("#2. Unique URL count: " + urlCount);
 		 //Answer for question #3
 		Utilities.logAnswers("#3. See Subdomains.txt");
 		 List<Frequency> frequencies = Crawler.subdomainCount();
 		 Utilities.printFrequencies(frequencies);
 		 
 		 //Answer for question #4
 		 System.out.println("4.) " + Ranking.longestPage(Crawler.textMap) + "is the longest page in the domain");
         // Page with highest number of words
 		 String highRankPage = Ranking.longestPage(Crawler.textMap);
 		 Utilities.logAnswers("#4. Longest Page: " + highRankPage);
 		 //Answer for questions #5
 		 List<Frequency> lf = Ranking.topWords(Crawler.textPages);
 		 Utilities.print500(lf);
 		
	}

}

package ir.assignments.three;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class Crawler extends WebCrawler{
	/**
	 * This method is for testing purposes only. It does not need to be used
	 * to answer any of the questions in the assignment. However, it must
	 * function as specified so that your crawler can be verified programatically.
	 * 
	 * This methods performs a crawl starting at the specified seed URL. Returns a
	 * collection containing all URLs visited during the crawl.
	 */
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	@Override
    public boolean shouldVisit(WebURL url) {
            String href = url.getURL().toLowerCase();
            return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ics.uci.edu/");
    }

	@Override
    public void visit(Page page) { 
			
            String url = page.getWebURL().getURL();
            crawl(url);
            System.out.println("URL: " + url);

            if (page.getParseData() instanceof HtmlParseData) {
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    String text = htmlParseData.getText();
                    String html = htmlParseData.getHtml();
                    List<WebURL> links = htmlParseData.getOutgoingUrls();

                    System.out.println("Text length: " + text.length());
                    System.out.println("Html length: " + html.length());
                    System.out.println("Number of outgoing links: " + links.size());
            }
    }

	public static Collection<String> crawl(String seedURL) {
		// TODO implement me
		List<String> list = new ArrayList<String>();
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Subdomains2.txt", true)))) {
            out.println(seedURL);
            list.add(seedURL);
            
        }catch (IOException e) {
        	System.out.println("IOException");
        }
		File file = new File("C:/Users/Dillon/workspace3/Assignment 3/Subdomains2.txt");
		List<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = WordFrequencyCounter.computeWordFrequencies(words);
		Utilities.printFrequencies(frequencies);
		return list;
	}
}

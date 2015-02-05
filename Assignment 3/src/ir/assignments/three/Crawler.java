package ir.assignments.three;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
	public static HashMap<String, Integer> textMap = new HashMap<String, Integer>();
	public static List<String> textPages = new ArrayList<String>();
	public static List<String> urlList = new ArrayList<String>();
	public static Map<String, Integer> urlFreq = new TreeMap<String, Integer>();
	public static Map<String, String> subdomainFreq = new TreeMap<String, String>();
	public static String searchDomain = null;
	private HashMap<String, Integer> map = new HashMap<String, Integer>();

	//Possibly try implementing a HashMap for optimality
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	@Override
    public boolean shouldVisit(WebURL url) {
            String href = url.getURL().toLowerCase();
            if (FILTERS.matcher(href).matches()) {
            	return false;
            }
            String curr = url.getDomain();    
            if (curr == null || !href.contains(searchDomain)){
                return false;
            }
            
            try {
				if (!intendToVisit(url.getURL())){
				    return false;
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            
            return true;
    }

	@Override
    public void visit(Page page) { 
			
            String url = page.getWebURL().getURL();
            String domain = page.getWebURL().getDomain();
            // Get the subdomain
            String subdomain = page.getWebURL().getSubDomain();
            String fullDomain = subdomain + "." + domain;
            subdomain = fullDomain.substring(0, fullDomain.indexOf("." + searchDomain));
             // Debug
            //System.out.println("URL:" + url);
            //
            
            crawl(url);
            logSubdomain(url, subdomain);
            //System.out.println("URL: " + url);
            
            if (page.getParseData() instanceof HtmlParseData) {
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    String text = htmlParseData.getText();
                   
                    int wordCounter = Ranking.countText(text);
                    textMap.put(url, wordCounter);
                    textPages.add(text);
                    System.out.println("URL: " + url + " Text length: " + text.length());
            }
    }
	
	public boolean intendToVisit(String url) throws URISyntaxException {
        // Determine if the page has been visited too many times (might be infinite loop)
        String s = URLHelper.removeQuery(url);
        if (s == null){
                return true;
        }

        int count = 1;
        if (this.map.containsKey(s)){
                count = map.get(s);
        }
       
        if (count >= 20){
                return false; //visit single page at most 20 times (with different query strings)
        }
        // Update with another intent
        this.map.put(s, count + 1);

        return true;
}

	public static void setSearchDomain(String domain){
		searchDomain = domain;
	}
	// Counting Unique URLs in the 
	public static Map<String, String> logSubdomain(String url, String subdomain){ 
  		//System.out.println("Logging "+ url + "for Domain:" + subdomain);
		String sd = subdomainFreq.get(url);   
  		if(sd == null){
  			// System.out.println("Adding:" + subdomain + " " + url);
  			subdomainFreq.put(url,subdomain);
  		}
		return subdomainFreq;	 
	}
	// Sub domain Counting
	// It is a two step process, we will go through each URL and subdomain
	// and create a Map of sub domains and # of URLs crawled in that
	// sub domain. Once this is done, then we will create the Frequency
	// list and return that.
	public static List<Frequency> subdomainCount(){
		List<Frequency> sdList = new ArrayList<Frequency>(); 
		Map<String, Integer> sdfreq = new TreeMap<String, Integer>();
		
		for(String key: subdomainFreq.keySet()){
			String subdomain = subdomainFreq.get(key);
			//Create the sub domain unique URL count
			Integer freq = sdfreq.get(subdomain);
			// System.out.println("Adding subdomain index" + subdomain);
			sdfreq.put(subdomain, (freq==null) ? 1 : freq+1);
		}
		// Now that we processed the unique URL count for each subdomain
		// Create the List with Subdomain frequencies
		for(String key: sdfreq.keySet()){
			sdList.add(new Frequency(key, sdfreq.get(key)));
		}
		System.out.println("# Unqiue Subdomains " + sdList.size());
		return sdList;
	}
	public static Map<String, Integer> crawl(String seedURL){ 
  		Integer freq = urlFreq.get(seedURL);    
		urlFreq.put(seedURL, (freq==null) ? 1 : freq+1);
		return urlFreq;	 
	}
	// Unique Urllist
	public static List<String> getUniqueUrls(){
		List<Frequency> uniqueUrlList = new ArrayList<Frequency>(); 
		for(String key: urlFreq.keySet()){
			urlList.add(key);
		}
		return urlList;
	}
}

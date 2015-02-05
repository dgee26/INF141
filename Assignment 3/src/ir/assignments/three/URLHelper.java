package ir.assignments.three;

import java.net.URI;
import java.net.URISyntaxException;

//https://code.google.com/p/cs221-awesome-team/source/browse/trunk/project2/Assignment%202/src/ir/assignments/three/helpers/URLHelper.java
public class URLHelper {
        public static String removeQuery(String url) throws URISyntaxException {
                
                URI uri = new URI(url.replace(" ", "%20"));
                return uri.getScheme() + "://" + uri.getAuthority() + uri.getPath();
               
        }

 
        public static String getDomain(String url) throws URISyntaxException {
          
        	 URI uri = new URI(url.replace(" ", "%20"));
             String host = uri.getHost();
             if (host != null) {
                     host = host.toLowerCase();
                     if (host.startsWith("www."))
                             host = host.substring("www.".length());

                     return host;
             }
             return null;
        }
}


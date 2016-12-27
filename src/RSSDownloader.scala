
package stefan.rss; 

import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class RSSDownloader (val source : String) 
{
	def download(url : String) : String = 
	{
		var urlObj = new URL(url);
		var con = urlObj.openConnection();
		con.setDoOutput(true);
		con.connect();
		var in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		var response = new StringBuilder();
		var inputLine = "";
		inputLine = in.readLine();
		while (inputLine != null) 
		{
			response.append(inputLine + "\n");
			inputLine = in.readLine();
		}
		in.close();
		return response.toString();
	}
	var content = download(source);
	var parser = new RSSParser(content);
	var channel = parser.channel;
	var articles = parser.articles;

}

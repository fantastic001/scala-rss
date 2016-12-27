


package stefan.rss; 

import java.util.Date; 

import java.io.FileWriter;
import java.io.PrintWriter; 
import java.text.SimpleDateFormat; 

class RSSArticle(val title : String, val desc : String, val content : String, val date : Date, val url : String) 
{
	def save(path: String) : Unit = {
		var formatter = new SimpleDateFormat("EE, dd MMM yyyy HH:mm:ss Z");
		var file = new FileWriter(path, false);
		var print = new PrintWriter(file);
		print.printf("{\"title\": \"%s\", \n\"description\": \"%s\", \n\"content\": \"%s\", \n\"date\": \"%s\", \n\"url\": \"%s\"}", title, desc, content, formatter.format(date), url);
		print.close();
	}
}

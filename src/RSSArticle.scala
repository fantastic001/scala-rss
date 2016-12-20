
package stefan.rss;


import java.util.Date; 

import java.io.FileWriter;
import java.io.PrintWriter; 

class RSSArticle(val title : String, val desc : String, val content : String, val date : Date, val url : String) 
{
	def save(path: String) : Unit = {
		var file = new FileWriter(path, false);
		var print = new PrintWriter(file);
		print.printf("{\"title\": \"%s\", \n\"description\": \"%s\", \n\"content\": \"%s\", \n\"date\": \"%s\", \n\"url\": \"%s\"}", title, desc, content, date.toString(), url);
		print.close();
	}
}

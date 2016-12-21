
package stefan.rss; 

import java.util.Date;


import java.io.FileWriter;
import java.io.PrintWriter; 

class RSSSource (val title : String , val desc : String , val url : String) 
{
	def save(path: String) : Unit = {
		var file = new FileWriter(path, false);
		var print = new PrintWriter(file);
		print.printf("{\"title\": \"%s\", \n\"description\": \"%s\", \n\"url\": \"%s\"}", title, desc, url);
		print.close();
	}
}


package stefan.rss; 

import java.util.Scanner; 
import java.io.File; 
import java.time.{ZonedDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat; 


class RSSDatabase (val root: String) 
{
	def restore_article(file: String) : RSSArticle = 
	{

		var filename = root + "/" + file; 
		val content = new Scanner(new File(filename)).useDelimiter("\\Z").next();
		var parser = new JSONParser(content); 
		return new RSSArticle(parser.json("title"),  parser.json("description"), parser.json("content"), new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse(parser.json("date")), parser.json("url"));
	}
	
	def get_articles() : Array[RSSArticle] = 
	{
		var folder = new File(root);
		var res = new Array[RSSArticle](0);
		for (f <- folder.listFiles()) 
		{
			res = res :+ restore_article(f.getName());
		}
		return res;
	}

	def save_article(a: RSSArticle) =
	{
		a.save(root + "/" + a.title);
	}


}

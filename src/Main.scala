
package stefan.rss;

import java.util.Date; 

import java.util.Scanner; 
import java.io.File; 

object Main
{
	def main(args: Array[String]) =
	{
		val content = new Scanner(new File("item.xml")).useDelimiter("\\Z").next();
		val parser = new RSSParser(content);
		//var article = new RSSArticle("stefan", "decription", "jkhjkhjk", new Date(), "url")
		var article = parser.getArticle(); 
		article.save("file.json");
	}
}

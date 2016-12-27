
package stefan.rss;

import java.util.Date; 

import java.util.Scanner; 
import java.io.File; 

object Main
{
	def main(args: Array[String]) =
	{
		//val content = new Scanner(new File("item.xml")).useDelimiter("\\Z").next();
		//val parser = new RSSParser(content);
		//var article = new RSSArticle("stefan", "decription", "jkhjkhjk", new Date(), "url")
		var downloader = new RSSDownloader("https://fantastic001.github.io/feed.xml");
		var article = downloader.articles(0);
		article.save("file.json");
		var db = new RSSDatabase("/me/data/Research and development/scala-rss/src");
		db.restore_article("file.json").save("file1.json");
		downloader.channel.save("source.json");
	}
}

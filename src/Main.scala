
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
		var articles = downloader.articles;
		var db = new RSSDatabase("/home/stefan/rss-db/");
		println("Saving articles");
		for (article <- articles) 
		{
			db.save_article(article);
		}
		println("Getting saved articles");
		for (article <- db.get_articles()) 
		{
			println(article.title);
		}
		downloader.channel.save("source.json");
	}
}

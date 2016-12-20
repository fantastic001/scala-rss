
package stefan.rss;

import java.util.Date; 

object Main
{
	def main(args: Array[String]) =
	{
		var article = new RSSArticle("stefan", "decription", "jkhjkhjk", new Date(), "url")
		article.save("file.json");
	}
}

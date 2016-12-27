
package stefan.rss; 

import java.util.Date; 
import java.text.SimpleDateFormat;

class RSSParser (val source : String)
{

	val lexer = new XMLLexer;
	val tokens = lexer.tokenize(source);

	val tape = new XMLTape(tokens);

	def tagname(name : String) = name.split(" ")(0);

	def parse() : RSSNode =
	{
		var node = new RSSNode();
		tape.expect("<");
		node.name = (tagname(tape.current()));
		while (tape.current() != ">") {
			tape.next();
			if (tape.current() == "/" && tape.following() == ">") 
			{
				return node; 
			}
		}
		tape.expect(">");
		while (tape.current() != "<" || tape.following() != "/") 
		{
			if (!tape.isBlank()) 
			{
				if (tape.current() == "<") 
				{
					node.childs = node.childs :+ parse();
				}
				else 
				{
					node.text = node.text + tape.current();
				}
			}
			tape.next();
		}
		tape.expect("<");
		tape.expect("/");
		tape.expect(node.name);
		tape.expect(">");
		return node;
	}
	while (tape.isBlank()) tape.next();
	if (tape.current() == "<" && (tape.following() contains "?xml")) 
	{
		while (tape.current() != ">") tape.next();
	}
	tape.next();
	while (tape.isBlank()) tape.next();
	var doc = parse().findElementByName("channel");

	var articles = new Array[RSSArticle](0);


	var channel = new RSSSource(doc.findElementByName("title").text, doc.findElementByName("description").text, doc.findElementByName("link").text);
	for (child <- doc.childs) 
	{
		if (child.name == "item")  
		{
			articles = articles :+ new RSSArticle(child.findElementByName("title").text, child.findElementByName("description").text, "", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse(child.findElementByName("pubDate").text), child.findElementByName("link").text);
		}
	}
}

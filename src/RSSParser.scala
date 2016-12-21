
package stefan.rss; 

import java.util.Date; 

class RSSParser (val source : String)
{

	val lexer = new XMLLexer;
	val tokens = lexer.tokenize(source);

	for (token <- tokens) println(token);
	val tape = new XMLTape(tokens);

	def parse() : RSSNode =
	{
		var node = new RSSNode();
		tape.expect("<");
		node.name = tape.current();
		while (tape.current() != ">") tape.next();
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
	var doc = parse().findElementByName("channel");
	var articles = new Array[RSSArticle](0);
	var channel = new RSSSource(doc.findElementByName("title").text, doc.findElementByName("description").text, doc.findElementByName("link").text);
	for (child <- doc.childs) 
	{
		if (child.name == "item")  
		{
			articles = articles :+ new RSSArticle(child.findElementByName("title").text, child.findElementByName("description").text, "", new Date(), child.findElementByName("link").text);
		}
	}
}

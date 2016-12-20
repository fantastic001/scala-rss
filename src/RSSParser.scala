
package stefan.rss; 

import java.util.Date; 

class RSSParser (val source : String)
{

	var title = "";
	var desc = ""; 
	var content = ""; 
	var url = ""; 
	//var date : Date; TODO will be defined later

	val lexer = new XMLLexer;
	val tokens = lexer.tokenize(source);

	val tape = new XMLTape(tokens);

	while (tape.isBlank()) tape.next();
	tape.expect("<");
	tape.expect("item");
	tape.expect(">");
	while (tape.isBlank()) tape.next();

	tape.expect("<");
	tape.expect("title");
	tape.expect(">");
	while (tape.current() != "<") 
	{
		title = title + tape.current;
		tape.next();
	}
	tape.expect("<");
	tape.expect("/title");
	tape.expect(">");


	while (tape.isBlank()) tape.next();

	tape.expect("<");
	tape.expect("description");
	tape.expect(">");
	while (tape.current() != "<") 
	{
		desc = desc + tape.current;
		tape.next();
	}
	tape.expect("<");
	tape.expect("/description");
	tape.expect(">");

	while (tape.isBlank()) tape.next();

	tape.expect("<");
	tape.expect("link");
	tape.expect(">");
	while (tape.current() != "<") 
	{
		url = url + tape.current;
		tape.next();
	}
	tape.expect("<");
	tape.expect("/link");
	tape.expect(">");
	

	while (tape.isBlank()) tape.next();
	var article = new RSSArticle(title, desc, "", new Date(), url);
	for (error <- tape.errors)println(error);

	def getArticle() : RSSArticle = article;
}

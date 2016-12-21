
package stefan.rss;

class XMLLexer 
{
	
	def tokenize(input : String) : Array[String] = {
		var current : String = "";
		var tokens : Array[String] = new Array[String](0);
		var state : Int = 0; 

		/*
			0: default ot whitespace
		*/
		for (c <- input) 
		{
			if (c == " " || c == "\n" || c == "\t")
			{
			}
			else if ("</>=\"" contains c) 
			{
				if (current != "") tokens = tokens :+ current; 
				state = 0;
				current = "";
				tokens = tokens :+ c.toString;
			}
			else 
			{
				current += c; 
			}
		}
		if (current != "") tokens = tokens :+ current; 
		return tokens; 
	}
}

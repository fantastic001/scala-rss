
package stefan.rss;

object XMLLexer 
{
	
	def tokenize(input : String) : Array[String] = {
		var current : String = "";
		var tokens : Array[String] = new Array[String](0);
		var state : Int = 0; 

		/*
			0: default ot whitespace
			1: number 
		*/
		for (c <- input) 
		{
			if (c == " " || c == "\n")
			{
			}
			else if ("<>=\"" contains c) 
			{
				if (current != "") tokens = tokens :+ current; 
				state = 0;
				current = "";
				tokens = tokens :+ c.toString;
			}
			else if (("0123456789" contains c) && state == 1) 
			{
				current += c.toString; 
			}
			else if (("0123456789" contains c) && state == 0) 
			{
				if (current != "") tokens = tokens :+ current; 
				state = 1; 
				current = c.toString; 
			}
			else if (!("0123456789" contains c) && state == 1) 
			{
				tokens = tokens :+ current; 
				current = c.toString; 
				state = 0; 
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

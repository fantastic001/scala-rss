
package stefan.rss; 

class JSONParser (val text : String) 
{
	var i : Int = 0;
	println(text);

	private def parse_blank() = 
	{
		println("Parsing blank");
		while (i < text.length && (" \t\n\r" contains text(i))) i = i + 1;
	}

	private def parse_string() : String = 
	{
		var str = "";
		while (text(i) != '"') {
			str = str + text(i);
			i = i + 1; 
		}
		println("Parsed string " + str);
		return str; 
	}

	private def parse_number() : Int = 
	{
		var str = ""; 
		while ("0123456789." contains text(i)) {
			str = str + text(i);
			i = i + 1; 
		}
		return str.toInt;
	}

	private def accept(c: String) : Boolean = 
	{
		if (c == text(i).toString()) 
		{
			println("Accepting " + c);
			i = i + 1;
			return true; 
		}
		return false; 
	}

	private def parse_map() : (String, String) = 
	{
		var m = Map();
		parse_blank();
		var k = "";
		var v = "";
		if (accept("\"")) 
		{
			k = parse_string();
		}
		if (accept("\"")) 
		{
			parse_blank();
			if (accept(":")) 
			{
				parse_blank();
				if (accept("\"")) 
				{
					v = parse_string();
					accept("\"");
				}
				else 
				{
					v = parse_number().toString();
				}
				parse_blank();
			}
		}
		println("Parsed map " + k + " -> " + v);
		return (k -> v);
	}

	private def parse_dict() : Map[String, String] = 
	{
		var m = Map[String, String]();
		parse_blank();
		println(text(i));
		if (accept("{")) {
			m += parse_map();
			while (accept(",")) m += parse_map();
			parse_blank();
			accept("}"); 
			println("Parsed dict");
		}
		return m;

	}

	var json = parse_dict();
}

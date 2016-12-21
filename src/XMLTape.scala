
package stefan.rss;

class XMLTape (input : Array[String]) 
{
	var symbols = input; 
	var index : Int = 0; 
	var errors : Array[String] = new Array[String](0);
	var accepted : String = "";
	var end = false; 
	def next() : Unit =
	{
		index = index + 1;
		if (index == symbols.length) end = true;
	}

	def accept(symbol : String) : Boolean = 
	{
		if (index >= symbols.length) return false; 
		if (symbols(index) == symbol) 
		{
			accepted = symbol;
			next();
			return true; 
		}
		else 
		{
			return false; 
		}
	}
	def expect(symbol : String) : Boolean =
	{
		if (accept(symbol)) 
		{
			return true; 
		}
		else 
		{
			errors = errors :+ "Expected " + symbol;
			return false; 
		}
	}

	def acceptNumber() : Boolean = 
	{
		if (symbols(index).matches("^\\d*$")) 
		{
			return accept(symbols(index));
		}
		else 
		{
			return false; 
		}
	}
	
	def error(msg : String) : Unit =
	{
		errors = errors :+ msg; 
	}

	def current() : String =
	{
		return symbols(index);
	}
	def following() : String = 
	{
		return symbols(index + 1);
	}

	def isBlank() : Boolean = 
	{
		return symbols(index).trim().isEmpty();
	}
}

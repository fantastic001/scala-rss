
package stefan.rss; 

class RSSNode 
{
	var name = ""; 
	var text = "";
	var childs = new Array[RSSNode](0);

	def findElementByName(name : String) : RSSNode = 
	{
		for (child <- childs) 
		{
			if (child.name == name) return child; 
			else 
			{
				var elem = child.findElementByName(name);
				if (elem != null) return elem;
			}
		}
		return null;
	}
}

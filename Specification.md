
Classes
==========================

RSSArticle
------------------

  * RSSArticle(title, description, content, date, url, source: RSSSource)
  * title -> String
  * description -> String
  * content -> String
  * url -> String
  * date -> DateTime
  * save() // saves article to JSON file on FS




RSSParser
----------------

  * RSSParser(source: String)
  * getArticle() -> RSSArticle


RSSSource
---------------

  * RSSSource(title, description, url)

RSSDownloader
-------------

  * RSSDownloader(urlOfFeed)
  * download(url) -> String 

RSSDatabase
----------

  * getArticles() -> List of RSSArticle
  * RSSDatabase(pathToRoot)

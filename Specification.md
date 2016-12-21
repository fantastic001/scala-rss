
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
  * getSource() -> RSSSource
  * getArticles() -> Array of RSSArticle


RSSSource
---------------

  * RSSSource(title, description, url)

RSSDownloader
-------------

  * RSSDownloader(RSSSource)
  * getArticles() -> List of RSSArticle
  * download(url) -> String 
  * downloadArticle(url) -> Article

RSSDatabase
----------

  * getArticles() -> List of RSSArticle
  * download(url) -> String 
  * downloadArticle(url) -> Article
  * RSSDatabase(pathToRoot)

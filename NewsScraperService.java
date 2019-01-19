import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsScraperService 
{
	//Utility function to Display all author list
	public void displayAuthor() throws IOException
	{
		String url = "https://www.thehindu.com/profile/?fldStartsWith=a&page=1";
	      char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	      //iterate through author name starting with each alphabet
	      for(int i=0;i<26;i++)
	      {
	    	  int pageNo=1;
	    	  //iterate through all pages per alphabet
	    	  while(true)
	    	  {
	    		  url = "https://www.thehindu.com/profile/?fldStartsWith=" + alphabet[i] +
	    				  "&page=" + Integer.toString(pageNo);
	    		  Document document = Jsoup.connect(url).get();
	    		  Elements author_list = document.getElementsByClass("auth-nm");
	    		  if(author_list.size() == 0)
	    		  {
	    			  break;
	    		  }
	    		  for (Element link : author_list) 
	    		  {
	    			  	String path = link.attr("href");
    			  		if(path.toLowerCase().contains("/author/".toLowerCase()))
	    			  	{
	    			  		System.out.println("Author Name - " + link.text());
	    			  		System.out.println("Profile link  - " + link.attr("href"));
	    			  		System.out.println("---------------------------------------");
	    			  	}
	    			}
	    		  pageNo++;
	    	  }
	      }
	}
	
	//Utility function to search author name 
	public String ShowAuthorDetails(String authorName)throws IOException
	{
		authorName.trim();
		if(authorName == null)
			return null;
		//list contains name of all author having author name
		ArrayList <String > name_list =  new ArrayList <String> ();
		//list contains profile link of all author having author name
		ArrayList <String > path_list =  new ArrayList <String> ();
		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		//iterate through author name starting with each alphabet
	    for(int i=0;i<26;i++)
	    {
	    	//iterate through all pages per alphabet
	    	int pageNo = 1;
			while(true)
			{
				String url = "https://www.thehindu.com/profile/?fldStartsWith=" + alphabet[i] +
					  "&page=" + Integer.toString(pageNo);
				Document document = Jsoup.connect(url).get();
	  		  	Elements author_list = document.getElementsByClass("auth-nm");
	  		  	if(author_list.size() == 0)
				  break;
	  		  	for (Element link : author_list) 
	  		  	{
				  	String path = link.attr("href");
			  		if(path.toLowerCase().contains("/author/".toLowerCase()))
				  	{
				  		//System.out.println("Title - " + link.text());
				  		String name = link.text();
				  		if(name.toLowerCase().contains(authorName.toLowerCase()))
				  		{
				  			name_list.add(name);
				  			path_list.add(path);
				  		}
				  	}
				}
				pageNo++;
			}
	    }
	    
		if(name_list.size() == 0)
		{
			System.out.println("No author name found");
			return null;
		}
		else if(name_list.size() == 1)
		{
			
			String[] bits = path_list.get(0).split("/");
			String lastOne = bits[bits.length-1];
			System.out.println("One name found " + lastOne);
			return lastOne;
		}
		else
		{
			System.out.println("There are multiple author with the same name");
			System.out.println("Please select one");
			for (int i = 0; i < name_list.size(); i++)
			{
	            System.out.println(i+1 + " " + name_list.get(i) + "\n" + path_list.get(i)); 
			}
			Scanner sc = new Scanner(System.in);
			int x = sc.nextInt();
			if(x >= name_list.size())
			{
				System.out.println("Invalid input");
				return null;
			}
			String[] bits = path_list.get(x-1).split("/");
			String lastOne = bits[bits.length-1];
			return lastOne;
		}
	}
	
	//Utility function to search article based on author name
	public void searchArticleBasedOnAuthorName(String authorName) throws IOException
	{
		authorName.trim();
		//get author id from author name
		authorName = ShowAuthorDetails(authorName);
		if(authorName == null)
			return;
		int pageNo=1;
		String url = "https://www.thehindu.com/profile/author/?page=1&urlSuffix="+authorName;
		
		//iterate through all pages of articles for a given author
		while(true)
		{
			url = "https://www.thehindu.com/profile/author/?page=" + Integer.toString(pageNo) 
				+ "&urlSuffix="+authorName;
			Document document = Jsoup.connect(url).get();
			Elements article_list = document.getElementsByClass("story-card-33");
			if(article_list.size() == 0)
			{
				break;
			}
			for (Element link : article_list) 
  		  	{
  			  	String path = link.attr("href");
  			  	Elements x = link.getElementsByClass("story-card-33-heading");
  			  	System.out.println("Title - " + x.text());
  			  	System.out.println("path - " + link.select("a[href]").attr("href"));
  			  	System.out.println("------------------------------------------");
  			}
			
			article_list = document.getElementsByClass("story-card-news");
			if(article_list.size() == 0)
			{
				break;
			}
			//System.out.println("data1 is " + url + " " + article_list.size());
			for (Element link : article_list) 
  		  	{
  			  	String path = link.select("h3").select("a[href]").attr("href");
  			  	System.out.println("Title - " + link.select("h3").select("a[href]").text());
  			  	System.out.println("Path - " + path);
  			  	System.out.println("------------------------------------------");
  			}
			
  		  pageNo++;
		}
	}
	
	//Utility function to search based on title and description
	public void SearchArticleBasedOnTitleAndDescription(String keyword)throws IOException
	{
		String url;
		int pageNo = 1;
		keyword.trim();
		
		//iterate through all pages containing given keyword
		while(true)
		{
			url = "https://www.thehindu.com/search/?q=" + keyword + "&order=DESC&sort=publishdate&page=" 
					+ pageNo;
			Document document = Jsoup.connect(url).get();
			Elements article_list = document.getElementsByClass("story-card75x1-text");
			if(article_list.size() == 0)
			{
  			  break;
			}
  			for (Element link : article_list) 
  		  	{
  			  	String path = link.attr("href");
  			  	Document doc = Jsoup.connect(path).get();
  			  	Elements metaTags = doc.getElementsByTag("meta");
  			  for (Element metaTag : metaTags) 
  			  {
  				String title = doc.select("meta[name=title]").first().attr("content");  
  	            String description = doc.select("meta[name=description]").get(0).attr("content");  
  				  if(title.toLowerCase().contains(keyword.toLowerCase()) || 
  			  			description.toLowerCase().contains(keyword.toLowerCase())	)
  				  {
  					  System.out.println("Title - " + title);
  					  System.out.println("Description - " + description);
  					  System.out.println("path - " + path);
  					  System.out.println("----------------------------------------");
  					  break;
  				  }
  			  }
  			  
  		//	  	System.out.println("Path is - " + path);
  			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
			NewsScraperService instance_ = new NewsScraperService();
			Scanner sc = new Scanner(System.in);
			System.out.println("Select an option");
			System.out.println("1.Display Author\n2.Search Article by author name\n"
					+ "3.Diplay article based on title and description");
			int input = sc.nextInt();
			switch(input)
			{
				case 1: instance_.displayAuthor();
						break;
				case 2: System.out.println("Enter author name");
						String author_name = sc.next();
						instance_.searchArticleBasedOnAuthorName(author_name);
						//instance_.searchArticleBasedOnAuthorName("aditya");
						break;
				case 3: System.out.println("Enter Keyword");
						String keyword = sc.next();
						instance_.SearchArticleBasedOnTitleAndDescription(keyword);
						//instance_.SearchArticleBasedOnTitleAndDescription("Hello");
						break;
				default: 
						System.out.println("Invalid input");
						break;
			}
	   }
	
}

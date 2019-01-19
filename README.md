# NewsScraper
A REST service which collects data of a newspaper and operates following functions:-  
 - Search available authors  
 - Search articles based on author name  
 - Search articles based on article title and description  
 
 ## Technology used  
  - Java
  - JSoup  
  
## Installation
 - Download Jsoup library from https://jsoup.org/download
 - Add Jsoup library to eclipse project in external jar section. For more details , please follow this link http://www.oxfordmathcenter.com/drupal7/node/44  
 
 ## How to run  
 - Create a new java project in eclipse.
 - Add newsScraperService.java file to your project.  
 - Run Project and provide required input.  
 
 ## Test Examples  
  #### Example 1  
   Select an option  
1.Display Author  
2.Search Article by author name  
3.Diplay article based on title and description  
1  
Author Name - Akshaya A.  
Profile link  - https://www.thehindu.com/profile/author/Akshaya-A.-20203/    
Author Name - Aditya Anand  
Profile link  - https://www.thehindu.com/profile/author/Aditya-Anand-7843/  
[More results ... ]  
#### Example 2  
Select an option  
1.Display Author  
2.Search Article by author name  
3.Diplay article based on title and description  
2  
Enter author name  
aditya  
There are multiple author with the same name  
Please select one  
1 Aditya Anand  
https://www.thehindu.com/profile/author/Aditya-Anand-7843/  
2 Aditya Mani Jha  
https://www.thehindu.com/profile/author/Aditya-Mani-Jha-5164/  
3 Aditya Valiathan Pillai  
https://www.thehindu.com/profile/author/Aditya-Valiathan-Pillai-30476/  
4 P Srinivas Aditya  
https://www.thehindu.com/profile/author/P-Srinivas-Aditya-405/  
1  
Title - IndiGo A320neo engines failed 69 times, says DGCA  
path - https://www.thehindu.com/news/national/indigo-a320neo-engines-failed-69-times-says-dgca/article26030747.ece  
Title - Mumbai’s Gulshan Mahal, Indian cinema’s new address  
path - https://www.thehindu.com/entertainment/movies/mumbais-gulshan-mahal-indian-cinemas-new-address/article26017783.ece  
[More results ... ]  

#### Example 3  
Select an option  
1.Display Author  
2.Search Article by author name  
3.Diplay article based on title and description  
3  
Enter Keyword  
Hello  
Title - Say hello to Kochi Blue Spikers  
Description - A State disappointed with the Yellow Army gets a new team to cheer  
path - https://www.thehindu.com/todays-paper/tp-sports/say-hello-to-kochi-blue-spikers/article26020897.ece  
Title - 16JANERSPSHellG1T59C5C33jpgjpg  
Description - Superintendent of Police S. Sakthi Ganesan launching the ‘Hello Seniors’ project in Erode on Tuesday. HANDOUT_E_MAIL  
path - https://www.thehindu.com/todays-paper/tp-national/tp-tamilnadu/lhihk5/article26009206.ece  
[More results ...]  

## API Used  
 - displayAuthor() //List all authors  
 - searchArticleBasedOnAuthorName(var authorName) //List all articles of an author , if multiple author first displays all the names , then select the desired author.  
 - SearchArticleBasedOnTitleAndDescription(var authorName) //List all articles based on title and description

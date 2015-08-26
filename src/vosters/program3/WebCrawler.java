package vosters.program3;

/** ******************************************************************** */
/* Starting at a URL inputted by the user, this will work through        */
/* all hyperlinks and document which links are broken on which page.     */
/* It will then display the broken link report to the user at the end.   */
/* Created by Jacob Vosters                                              */
/* Date last modified:  8/26/2015                                        */
/*********************************************************************** */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class WebCrawler {

	public static void main(String[] args) {
		//Setup Arraylists
		ArrayList<URL> pagesVisited = new ArrayList<URL>();
		ArrayList<URL> pagesToVisit = new ArrayList<URL>();
		ArrayList<ArrayList<String>> brokenLinks = new ArrayList<ArrayList<String>>();

		//get starting URL from user
		URL startingURL = getStartingURLFromUser();

		pagesToVisit.add(startingURL);


		while(!pagesToVisit.isEmpty()){ //loop through all the pages
			ArrayList<String> links = linkParser(htmlReader(pagesToVisit.get(0))); //parse the links from the current page's html contents


			ArrayList<String> curLink = new ArrayList<String>();
			brokenLinks.add(curLink);
			while(!links.isEmpty()){ //loop through all the links to determine if they are broken

				//if the link is broken
				if(isBrokenLink(pagesToVisit.get(0), links.get(0))){
					if(curLink.isEmpty()){
						//Set the page the broken link is on to element 1 of the broken links array
						curLink.add(pagesToVisit.get(0).toString());
					}

					curLink.add(links.get(0)); //Add the link to the brokenLinks array
					links.remove(0);
				} else {

					//if the link is NOT broken
					try {
						//We don't want to actually go to external websites (in Dr. Stevensons test environment), so skip going to the goduke link for now
						if(links.get(0).indexOf("www.goduke") < 0){

							//Adds link to pages to visit (if it's not already on our list and we already haven't visited it)
							if(!pagesToVisit.contains(new URL(pagesToVisit.get(0),links.get(0))) && !pagesVisited.contains(new URL(pagesToVisit.get(0),links.get(0)))){
								pagesToVisit.add(new URL(pagesToVisit.get(0),links.get(0)));
							}

						}
					} catch (MalformedURLException e) {
					}
					links.remove(0);
				}
			}
			//mark the page as already visited
			pagesVisited.add(pagesToVisit.get(0)); 
			pagesToVisit.remove(0);
		}

		//display the broken links in the arraylist
		displayBrokenLinkReport(brokenLinks);
	}



	public static URL getStartingURLFromUser(){
		URL startingURL = null;
		try {
			//*********************************************************************
			// Get the HTML from the user
			//*********************************************************************	
			//URL startingURL = new URL(JOptionPane.showInputDialog("Please enter a URL: "));
			startingURL = new URL("http://www.cs.uwec.edu/~stevende/cs145testpages/default.htm"); //Hard coded			

		} catch (MalformedURLException e) {
			System.out.println("Bad URL");
			System.exit(0);
		}
		return startingURL;
	}

	public static String htmlReader(URL webURL){

		String htmlContents = "";

		URLConnection con;
		try {
			//*********************************************************************
			// Read all the HTML of the page to a string, htmlContents
			//*********************************************************************
			con = webURL.openConnection();

			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;

			while((line = br.readLine()) != null) {
				htmlContents += line + " ";
			}
			br.close();			

		} catch (IOException e) {
			System.out.println("Error reading HTML " + webURL + "\n      " + e);
			System.exit(1);
		}
		//********************************
		// Make it lower case and return it
		htmlContents = htmlContents.toLowerCase(); 

		return htmlContents;
	}

	public static ArrayList<String> linkParser(String htmlContents){
		//*********************************************************************
		// Parse through the links in the page and put them into an ArrayList
		//*********************************************************************
		ArrayList<String> urls = new ArrayList<String>();
		int index = 0;
		while(htmlContents.indexOf("<a href=",index) != -1){ //while there is still another anchor left in the string
			urls.add(htmlContents.substring(htmlContents.indexOf("<a href=", index) + 9,htmlContents.indexOf("\"", htmlContents.indexOf("<a href=", index) + 9)));
			index = htmlContents.indexOf("\"", htmlContents.indexOf("<a href=", index) + 9);
		}
		return urls;

	}

	public static boolean isBrokenLink(URL baseURL, String theHREF){
		URL newLink;

		//*************************************
		//Check to see if the link is malformed
		try {
			newLink = new URL(baseURL,theHREF);
		} catch (MalformedURLException e) {
			return true;
		}

		//*************************************
		//Check to see if the server exists		
		URLConnection con;
		try {
			con = newLink.openConnection();
			con.connect();
		} catch (IOException e) {
			return true;
		}

		//*************************************
		//Check to see if there is a 4xx client error 
		HttpURLConnection httpProtocol = (HttpURLConnection)con;
		try {
			System.out.println(httpProtocol.getResponseCode() + "------------------" + new URL(baseURL,theHREF).toString()); //print page response code
			if(httpProtocol.getResponseCode() >= 400){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false; //return false if this is a working link!
	}

	public static void displayBrokenLinkReport(ArrayList<ArrayList<String>>brokenLinks){

		System.out.println("\nBroken Link Report: ");
		for(int i = 0; i < brokenLinks.size(); i++){
			for(int j = 0; j < brokenLinks.get(i).size();j++){
				if(j == 0){
					System.out.println("    Page: " + brokenLinks.get(i).get(j));
				} else {
					System.out.println("         Broken link: " + brokenLinks.get(i).get(j));
				}
			}
		}





	}
}

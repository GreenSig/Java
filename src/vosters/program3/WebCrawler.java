package vosters.program3;

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
		URL startingURL = getStartingURLFromUser();
		String test = htmlReader(startingURL);
		linkParser(test);
		System.out.println(test);
		System.out.println(isBrokenLink(startingURL, "http://www.cs.uwec.edu/~stevende/cs145testpages/page1.htm"));
	}



	public static URL getStartingURLFromUser(){
		URL startingURL = null;
		try {
			//*********************************************************************
			// Get the HTML from the user
			//*********************************************************************	
			//URL startingURL = new URL(JOptionPane.showInputDialog("Please enter a URL: "));
			startingURL = new URL("http://www.cs.uwec.edu/~stevende/cs145testpages/page1.htm"); //Hard coded	

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
			System.out.println("Error reading HTML");
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
			urls.add(htmlContents.substring(htmlContents.indexOf("<a href=", index) + 9,htmlContents.indexOf("\">", htmlContents.indexOf("<a href=", index))));
			index = htmlContents.indexOf("\">", htmlContents.indexOf("<a href=", index));
		}
		return urls;
		
	}

	public static boolean isBrokenLink(URL baseURL, String theHREF){
		URL newLink;
		
		//*************************************
		//Check to see if the link is malformed
		try {
			newLink = new URL(theHREF);
		} catch (MalformedURLException e) {
			return true;
		}
		
		//*************************************
		//Check to see if the server exists		
		URLConnection con;
		try {
			con = newLink.openConnection();
		} catch (IOException e) {
			return true;
		}
		
		//*************************************
		//Check to see if there is a 404 error
		HttpURLConnection httpProtocol = (HttpURLConnection)con;
		try {
			if(httpProtocol.getResponseCode() == 404){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false; //return false if this is a working link!
	}

	public static void displayBrokenLinkReport(ArrayList<ArrayList<String>>brokenLinks){
	}
}

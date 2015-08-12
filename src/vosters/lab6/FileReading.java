package vosters.lab6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReading {

	public static void main(String[] args) {

		String fileContents = "";
		//System.out.print(new File("").getAbsolutePath() + "\n");

		
		///////////////////////////////////////
		//Read in the file
		///////////////////////////////////////
		try {     
			FileReader fr = new FileReader("C:/Users/N059251/workspace/labs/src/vosters/lab6/sample.txt");
			BufferedReader br = new BufferedReader(fr);

			String line;
			while((line = br.readLine()) != null) {
				fileContents += line + " ";

			}


			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.print(fileContents);
		
		
		///////////////////////////////////////
		//Split the string and count the words
		///////////////////////////////////////
		String[] wordList = fileContents.split("\\s"); //splits the string according to a regular expression
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> wordcounts = new ArrayList<Integer>();

		for(int i = 0; i < wordList.length; i++){
			if(!words.contains(wordList[i])){ //if the word is not found yet add it
				words.add(wordList[i]);
				wordcounts.add(1);
			} else { //if the word is found increment its count
				wordcounts.set(words.indexOf(wordList[i]), wordcounts.get(words.indexOf(wordList[i])) + 1);
			}
		}
		
		///////////////////////////////////////
		//Sort the words in descending order
		///////////////////////////////////////

		System.out.print(fileContents);

	}


}



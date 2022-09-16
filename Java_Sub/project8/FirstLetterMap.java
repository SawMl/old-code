package project8;
/**
 * @author Shawn Anthony
 * November 1, 2021
 * CECS 277 Project 8
 * 
 * This program reads all the words from a text file and adds them to a map
 * whose keys are the first letters of the words and whose values are sets of
 * words that start with that same letter. Then prints all the word sets in 
 * alphabetical ordder
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
public class FirstLetterMap {

	public static void main(String[] args) throws IOException {
		
		FileInputStream inputStream; //file reader object
		boolean found = false; //flag variable for if the file was found
		String fileName = "test1.txt";//initial filename
		Scanner in = new Scanner(System.in); //in case we need to retrieve the filename from console
		while (!found) {
			try
			{
				inputStream = new FileInputStream(fileName);
				found = true;
				Scanner input = new Scanner(inputStream);
				HashMap<Character,Set<String>> map = new HashMap<Character,Set<String>>();
				
				String s;
				char c = 'a';
				Set<String> set; 
				
				while (input.hasNext()) { //while the file has data
					s = input.next(); //examine next token
					s = s.replaceAll("[^a-zA-Z]", ""); //Removes non-alpha characters
					s = s.toLowerCase(); //To protect against duplicates
					if (!s.isEmpty()) { //To protect against Strings that were all symbols
						c = s.charAt(0); //retrieve the first character of the string
						if (map.containsKey(c)) { //check if the character is already in the map
							map.get(c).add(s); //if so, add the string to the set assigned to that char
						}
						else {
							set = new TreeSet<String>(); //otherwise start a new set
							set.add(s); //make a new map entry
							map.put(c,set);
						}
					}
				}
				
				//For each entry in the map, pring the contents
				//1 line for each key
				map.entrySet().forEach( entry -> {
					System.out.println("["+entry.getKey()+"] " + entry.getValue());	
				});
				input.close();
			}
			catch(FileNotFoundException e) 
			{
				System.out.print("File not found...\n Enter the file name:");
				fileName = in.next();
			}
		}
		in.close();
	}
}

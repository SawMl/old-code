/*
 * Shawn Anthony
 * September 17, 2021
 * CECS 277
 * 
 * This program asks the user to input the name for a .txt file
 * The program then reads the data as a (2) string (5) double format
 * ex:
 * 
 * first1 last1 99 99 99 99 99
 * first2 last2 99 99 99 99 99
 * 
 * and so on...
 * 
 * The program then calculates the averages after dropping the lowest score
 * and prints it next to the name formatted to result.txt
 * 
 * Class Average computed and printed at the very end
 */



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;

public class project3 {

	public static ArrayList<Double> averages;
	public static ArrayList<String> names;
	public static ArrayList<ArrayList<Double>> testScores;
	public static ArrayList<String> letterGrade;
	public static FileInputStream inputStream;
	public static Scanner input;
	public static PrintWriter out;
	public static String outputName;
	
	
	//Method for reading and storing the data
	public static void readData() throws FileNotFoundException {
		inputStream = new FileInputStream(getFileName());
		input = new Scanner(inputStream);
		Scanner in;
		String line = "", n="";
		
		//counters used to specify indices in the ArrayLists
		int cnt1=-1, cnt2=-1;
		
		//Loops while file has data to read
		while(input.hasNext()) {
			cnt1++;
			
			//create a line to read
			line = input.nextLine();
			in = new Scanner(line);
			
			//concatenate the first and last name into one string
			n=in.next();
			n+=" ";
			n+=in.next();
			names.add(n);
			testScores.add(new ArrayList<Double>());
			while (in.hasNextDouble()) {
				cnt2++;
				testScores.get(cnt1).add(cnt2,in.nextDouble());
			}
			cnt2=-1;
		}
		input.close();
		
		//Needed the try and catch to close the inputStream
		try {
			inputStream.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method to calculate the average test scores for a student and grades
	public static double calculateAverage(int index) {
		double avg=0;
		
		//Add up all the scores for a student 
		for (int j=0;j<testScores.get(index).size();j++) {
			avg+=testScores.get(index).get(j);
		}
		
		//drop the lowest score before computing the average 
		avg = avg - findLowestScore(index);
		avg = avg/(testScores.get(index).size()-1);
		
		averages.add(index,avg);
		
		//With the average calculated, determine the letter grade
		//and save it to the static ArrayList
		if (avg>=98) {
			letterGrade.add(index,"A+");
		}
		else if (avg>=95) {
			letterGrade.add(index,"A");
		}
		else if (avg>=91) {
			letterGrade.add(index,"A-");
		}
		else if (avg>=88) {
			letterGrade.add(index,"B+");
		}
		else if (avg>=84) {
			letterGrade.add(index,"B");
		}
		else if (avg>=80) {
			letterGrade.add(index,"B-");
		}
		else if (avg>=75) {
			letterGrade.add(index,"C+");
		}
		else if (avg>=70) {
			letterGrade.add(index,"C");
		}
		else if (avg>60) {
			letterGrade.add(index,"D");
		}
		else  {
			letterGrade.add(index,"NC");
		}
		
		
		
		return avg;
	}
	
	//Method to find the lowest score for a student
	public static double findLowestScore(int index) {
		double low=Double.MAX_VALUE;
		for (int j=0;j<testScores.get(index).size();j++) {
			if (testScores.get(index).get(j)<low) {
				low = testScores.get(index).get(j);
			}
		}
		return low;
	}
	
	//Method for outputting to file as a formatted table
	//Throws the FileNotFoundException, but will create the file if DNE
	public static void writeData() throws FileNotFoundException {
		double a;
		
		out = new PrintWriter(outputName);
		out.printf("Student Name               Average            Final Grade \n");
		out.printf("==========================================================\n");
		for (int i=0;i<names.size();i++) {
			a=calculateAverage(i);
			out.printf("%-28s",names.get(i));
			out.printf("%4.2f",a);
			out.printf("%20s %s \n","",letterGrade.get(i));
		}
		out.printf("==========================================================\n");
		out.println();
		out.printf("%-28s", "Class Average:");
		out.printf("%4.2f",classAverage());
		out.close();
	}
	
	//method for calculating the classAverage
	public static double classAverage() {
		double avg=0;
		int classSize = names.size();
		for (int i=0;i<classSize;i++) {
			avg+=averages.get(i);
		}
		avg=avg/classSize;
		
		return avg;
	}
	
	//Main function
	public static void main(String[] args){
		
		names = new ArrayList<String>();
		testScores = new ArrayList<ArrayList<Double>>();
		letterGrade = new ArrayList<String>();
		averages = new ArrayList<Double>();
		
		
		//Tries the simple algorithm
		//If file not found, it lets the user know and tries again
		
		
		try {
			readData();
			writeData();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		

	}
	
	
	//Method for getting the file name from the zero
	public static String getFileName() {
		input = new Scanner(System.in);
		String name = "";
		System.out.println("Input file name:");
		name=input.nextLine();
		System.out.println("Output file name:");
		outputName=input.nextLine();
		input.close();
		return name;
	}

}

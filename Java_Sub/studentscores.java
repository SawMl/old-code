

/*Shawn Anthony
 * 9/7/2021
 * 
 * This program takes a list of names and 5 scores for each name and
 * computes the average and prints out a nicely formatted table to display the results
 */


import java.util.Scanner;
public class studentscores {

	/*
	 * Initialize the arrays outside of main so they can be worked on by the functions
	 */
	
	static double[][] studentScores;
	static String[] students;
	static Scanner input = new Scanner(System.in);
	static int numStudents;
	
	
	/*
	 * Print function
	 * Displays a table
	 */
	
	static void print() {
		int spaces=0;
		System.out.println();
		System.out.println(" Student Name         ex1|  ex2|  ex3|  ex4|  ex5| Average");
		System.out.println("==========================================================");
		for (int i=0;i<numStudents;i++) {
			spaces=20-students[i].length();
			System.out.print(" " + students[i]);
			for (int x=0;x<spaces;x++) {
				System.out.print(" ");
			}
			for (int j=0;j<5;j++) {
				System.out.printf("%.2f",studentScores[i][j]);
				System.out.print(" ");
			}
			System.out.printf("%.2f",average(studentScores[i]));
			System.out.println();
		}
	}
	
	//function for finding the lowest score in an array
	static double findLowest(double[] scores) {
		double lowest=Double.MAX_VALUE;
		for (int i=0;i<scores.length;i++) {
			if (scores[i]<lowest) {
				lowest=scores[i];
			}
		}
		return lowest;
	}
	
	//function for finding the highest score in an array
	static double findHighest(double[] scores) {
		double highest = Double.MIN_VALUE;
		for (int i=0;i<scores.length;i++) {
			if (scores[i]>highest) {
				highest=scores[i];
			}
		}
		return highest;
	}
	
	//function for calculating the average
	static double average(double[] scores) {
		double avg=0.0;
		for (int i=0;i<scores.length;i++) {
			avg+=scores[i];
		}
		avg=avg-findHighest(scores)-findLowest(scores);
		avg=avg/3;
		
		return avg;
	}
	
	// for validating user input
	static boolean validateUserInput(double score) {
		
		if(score<0 || score>100) {
			return false;
		}
		
		return true;
	}
	
	//function for getting the student info
	static void getStudentInfo() {
		
		String name="";
		//For moving the input cursor:
		name = input.nextLine();
		
		//for each student, exectute loop gathering name and scores
		for (int i=0;i<numStudents;i++) {
			System.out.println("Input Student "+(i+1)+"'s name:");
			name = input.nextLine();
			students[i]=name;
			//for each score, input the data
			for (int j=0;j<5;j++) {
				System.out.println("Input score " + (j+1) + " for " + name + ":");
				studentScores[i][j]=input.nextDouble();
				while (!validateUserInput(studentScores[i][j])) {
					System.out.println("Invalid input...");
					System.out.println();
					System.out.println("Input score " +j+1+" for " + name + " :");
					studentScores[i][j]=input.nextDouble();
				}
			}
			name = input.nextLine();
			name = "";
		}
		
		
	}
	
	/*
	 * Main Function
	 * Asks the user for the number of students in the class
	 * Initializes the arrays for names and scores according to the class size
	 */
	
	public static void main(String[] args) {
		
		System.out.println("User, input the number of students in the class:");
		
		numStudents = input.nextInt();
		
		studentScores = new double[numStudents][5];
		students = new String[numStudents];

		getStudentInfo();
		print();
		
		
	}

}
/**
 * @author Shawn Anthony
 * CECS 277
 * Project 7
 * October 26, 2021
 * 
 * This program demonstrates use of inheritance, polymorphism,
 * interface, and aggregation through a grading system framework
 */
package project7;

//Base class GradedActivity
public class GradedActivity {
	
	//private field to contain the score
	private double score;
	private String name;
	
	//default constructor
	public GradedActivity() {
		score=0;
		name="";
	}
	
	//overloaded constructor#1
	public GradedActivity(double s) {
		score = s;
		name = "";
	}
	
	//overloaded constructor#2
	public GradedActivity(String n,double s) {
		score = s;
		name = n;
	}
	
	//Mutator method for name of the gradedactivity
	public void setName(String s) {
		name = s;
	}
	
	//accessor method for the name of the activity
	public String getName() {
		return name;
	}

	//Accessor for score
	public double getScore() {
		return score;
	}

	//Mutator for score
	public void setScore(double score) {
		this.score = score;
	}
	
	/**
	 * public getGrade() function
	 * @return the letter grade character (A-F) according to the score
	 */
	public char getGrade() {
		if (score>=90) {
			return 'A';
		}
		else if (score>=80) {
			return 'B';
		}
		else if (score>=70) {
			return 'C';
		}
		else if (score>=60) {
			return 'D';
		}
		return 'F';
	}
	
	//toString method to format any GradedActivity as a string.
	public String toString() {
		String s=name;
		s+=String.format(" score: %.2f Grade: ",score);
		s+=getGrade();
		return s;
	}
	
	
}

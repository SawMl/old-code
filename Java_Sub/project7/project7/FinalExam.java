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

//a sub class of Graded Activity to represent Final Exam objects
public class FinalExam extends GradedActivity {
	private int numQuestions;
	private double pointsEach;
	private int numMissed;
	
	//default constructor 
	public FinalExam() {
		numQuestions=0;
		pointsEach=0;
		numMissed=0;
		setName("Final Exam");
	}
	
	//overloaded constructor
	public FinalExam(int numQuestions, int numMissed) {
		this.numQuestions=numQuestions;
		this.numMissed=numMissed;
		pointsEach=100.0/(double) numQuestions;
		setName("Final Exam");
		setScore((numQuestions-numMissed)*pointsEach);
	}
	
	//Mutator method for pointsEach
	public void setPointsEach(double p) {
		pointsEach=p;
	}
	//Accessor method for pointsEach
	public double getPointsEach() {
		return pointsEach;
	}
	//Mutator method for number of missed questions
	public void setNumMissed(int n) {
		numMissed=n;
	}
	//Accessor method to retrieve the number of questions missed
	public int getNumMissed() {
		return numMissed;
	}
	//Mutator method for the total number of questions
	public void setNumQuestions(int n) {
		numQuestions=n;
	}
	//Acessor method to retrieve the total number of questions
	public int getNumQuestions() {
		return numQuestions;
	}
}

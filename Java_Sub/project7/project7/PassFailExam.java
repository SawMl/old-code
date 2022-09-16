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

public class PassFailExam extends PassFailActivity {
	private int numQuestions;
	private double pointsEach;
	private int numMissed;
	
	public PassFailExam() {
		super();
		numQuestions=0;
		pointsEach=0;
		numMissed=0;
		setName("Pass/Fail Exam");
	}
	
	public PassFailExam(int numQuestions, double pointsEach, int numMissed) {
		super();
		this.numQuestions=numQuestions;
		this.pointsEach=pointsEach;
		this.numMissed=numMissed;
		setName("Pass/Fail Exam");
	}
	
	public PassFailExam(int numQuestions, int numMissed, double minpassing) {
		super(minpassing);
		this.numQuestions=numQuestions;
		this.numMissed=numMissed;
		pointsEach=100.0/ (double) numQuestions;
		setScore(pointsEach* (double)(numQuestions-numMissed));
		setName("Pass/Fail Exam");
	}
	
	//accessor method for the total number of questions in the exam
	public int getNumQuestions() {
		return numQuestions;
	}

	//mutator method for setting the total number of questions in the exam
	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}

	//accessor method for the points each question is worth
	public double getPointsEach() {
		return pointsEach;
	}

	//Mutator method for changing the points each question is worth
	public void setPointsEach(double pointsEach) {
		this.pointsEach = pointsEach;
	}

	//accessor method for getting the number of questions missed by the exam taker
	public int getNumMissed() {
		return numMissed;
	}

	//mutator  method to change the number of questions missed by the exam taker
	public void setNumMissed(int numMissed) {
		this.numMissed = numMissed;
	}

	//toString method to format the object as a string to display the data
	@Override
	public String toString() {
		String s = getName()+" score: ";
		s+= String.format("%.2f",getScore())+" Grade: "+getGrade();
		return s;
	}
}

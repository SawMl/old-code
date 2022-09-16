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

//Class CourseGrades that implements the interface, Analyzable
public class CourseGrades implements Analyzable {
	private GradedActivity[] grades;
	private final int NUM_GRADES = 4;
	
	//default constructor
	public CourseGrades() {
		grades = new GradedActivity[NUM_GRADES]; //Initializes an array of GradedActivity objects
	}
	//mutator for the 1st object, lab, in the array of GradedActivities
	public void setLab(GradedActivity g) {
		grades[0]=g;
	}
	//mutator for the 2nd object, Pass Fail Exam, in the array of GradedActivities
	public void setPassFailExam(GradedActivity g) {
		grades[1]=g;
	}
	//mutator for the 3rd object, Essay, in the array of GradedActivities
	public void setEssay(GradedActivity g) {
		grades[2]=g;
	}
	//mutator for the 4th object, Final Exam, in the array of GradedActivities
	public void setFinalExam(GradedActivity g) {
		grades[3]=g;
	}
	
	//toString method that formats the data into a string
	@Override
	public String toString() {
		String s = String.format("Lab Score: %.2f Grade: ",grades[0].getScore());
		s+=grades[0].getGrade();
		s+=String.format("\nPass Fail Exam: %.2f Grade: ",grades[1].getScore());
		s+=grades[1].getGrade();
		s+=String.format("\nEssay: %.2f Grade: ",grades[2].getScore());
		s+=grades[2].getGrade();
		s+=String.format("\nFinal Exam: %.2f Grade: ",grades[3].getScore());
		s+=grades[3].getGrade();
		s+=String.format("\nAverage score: %.2f\nHighest score: %.2f\nLowest score: %.2f", getAverage(), getHighest().getScore(), getLowest().getScore());
		return s;
	}
	
	//Method to retrun the average of all the scores
	public double getAverage() {
		double s=0;
		for (int i=0;i<NUM_GRADES;i++) {
			s+=grades[i].getScore();
		}
		return s/NUM_GRADES;
	}
	//method to return the GradedActivity object with the highest score
	public GradedActivity getHighest() {
		double high =-1;
		double s;
		int j=0;
		for (int i=0;i<NUM_GRADES;i++) {
			s=grades[i].getScore();
			if(s>high) {
				high=s;
				j=i;
			}
		}
		return grades[j];
	}
	//method to return the GradedActivity object with the lowest score
	public GradedActivity getLowest() {
		double low =200;
		double s;
		int j=0;
		for (int i=0;i<NUM_GRADES;i++) {
			s=grades[i].getScore();
			if(s<low) {
				low=s;
				j=i;
			}
		}
		return grades[j];
	}
}

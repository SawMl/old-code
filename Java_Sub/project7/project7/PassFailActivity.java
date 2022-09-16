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

//a sub class of Graded Activity. A more specific type of GradedActivity
public class PassFailActivity extends GradedActivity {
	private double minPassingScore;
	
	//default constructor 
	public PassFailActivity() {
		super();
		minPassingScore=0;
	}
	
	//Overloaded constructor given the minimum passing score
	public PassFailActivity(double min) {
		super();
		minPassingScore=min;
	}
	
	//Accessor method for minimum passing score
	public double getMinPassingScore() {
		return minPassingScore;
	}
	
	//Override for the getGrade function
	//Pass Fail activities are only either P or F
	@Override
	public char getGrade(){
		if (super.getGrade()!='F') {
			return 'P';
		}
		return 'F';
	}
}

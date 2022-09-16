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

//The interface Analyzable
public interface Analyzable {

	public double getAverage();
	public GradedActivity getHighest();
	public GradedActivity getLowest();
}

package project9;
/*
 * @author Shawn Anthony
 * CECS 277 Project 9
 * November 10, 2021
 * 
 * This program demonstrates implementation of a to do list.  Tasks have a 
 * priority between 1 and 9, and a description. When the user enters the command
 * "add" [priority] "description",the program adds a new task. When the user enters
 * "next", the program removes and prints the most urgent task. The "quit" command 
 * quits the program
 */


//The Task object
public class Task implements Comparable<Task>{

	private String description;
	private int priority;
	
	//default constructor
	public Task() {
		description=null; //no description
	}
	
	//overloaded constructor
	public Task(int priority, String description) {
		this.priority=priority;
		this.description=description;
	}
	
	//priority accessor
	public int getPriority() {
		return priority;
	}
	
	//description accessor
	public String getDescription() {
		return description;
	}
	
	//compareTo method
	//Used when sorting
	//Compares the priority value
	@Override
	public int compareTo(Task other) {
		return other.priority-this.priority;
	}
	
	//hashCode method
	//Returns the hashcode of the task
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	//equals method
	//checks if two Tasks have the same description
	public boolean equals(Task other) {
		if (this.description.equalsIgnoreCase(other.description))
			return true;
		return false;
	}
	
}

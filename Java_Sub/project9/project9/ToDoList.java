package project9;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Scanner;
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

//ToDoList object class to utilize the Task class
public class ToDoList {
	
	private PriorityQueue<Task> queue;

	//default constructor
	public ToDoList() {
		queue = new PriorityQueue<Task>(); //creates a new queue
	}
	
	//a default general command gate for any String
	public void processString(String s) {
		validateFormat(s);
	}
	
	//a specific addTask function
	public void addTask(String s) {
		validateFormat(s);
	}
	
	//a specific nextTask function
	public void nextTask() {
		validateFormat("next");
	}
	
	//validates the command keyword by string matching
	private boolean validateCommand(String s) {
		if (s.equalsIgnoreCase("Add")) {
			return true;
		}
		else if (s.equalsIgnoreCase("Nex")) {
			return true;
		}
		else if (s.equalsIgnoreCase("Qui")) {
			return true;
		}
		return false;
	}
	
	//Checks to make sure the priority number is valid
	//prints a message if not
	private boolean validatePriority(int num) {
		if (num<1 || num>9) {
			System.out.println("The priority must be an integer between 1 and 9.");
			return false;
		}
		return true;
	}
	
	//function to quit the program
	private void quitProgram() {
		System.out.print("Press any key to continue...");
		Scanner input = new Scanner(System.in);
		input.next();
		input.close();
		System.exit(0);
	}
	
	//function to extract the priority number from the string
	private int getPriorityNum(String s) {
		Scanner in = new Scanner(s);
		String command = in.next();
		int num=-1;
		if (in.hasNextInt()) {
			num = in.nextInt();
		}
		in.close();
		return num;
	}
	
	//function to extract the description from the string
	private String getDescription(String s) {
		Scanner in = new Scanner(s);
		in.next();
		in.next();
		String description = in.nextLine();
		in.close();
		description = description.trim();
		return description;
	}
	
	//function for extracting the command from the string
	private String getCommand(String s) {
		return s.substring(0,3);
	}
	
	//function to make sure the description is not already in the queue
	private boolean validateDescription(String s) {
		Iterator<Task> iterator = queue.iterator();
		String t;
		while (iterator.hasNext()) {
			t=iterator.next().getDescription();
			if (s.equalsIgnoreCase(t)) {
				return false;
			}
		}
		return true;
	}
	
	//main function to both validate the format of the inputted string
	//as well as execute it
	private boolean validateFormat(String s) {
		String command = getCommand(s);
		//System.out.println(command);
		if (validateCommand(command)) {
			char c = s.charAt(0); //Already validated the string, so just look at the first character
			c = Character.toUpperCase(c);
			if (c=='A') {
				int num = getPriorityNum(s);
				if (!validatePriority(num)) { //If the priority number cant be validated
					return false;
				}
				String description = getDescription(s);
				if (validateDescription(description)) { //Once the description has been validated
					add(num,description);
					return true;
				}
				System.out.println("The task already exists in the ToDoList, try again..");
				return false;
			}
			else if (c=='N') {
				System.out.println(next());
				return true;
			}
			else if (c=='Q') {
				quitProgram();
				return true;
			}
			return false;
		}
		System.out.println("Can't validate command format... Try again");
		return false;
	}
	
	//adds the new task after validation of components 
	private void add(int priority,String s) {
		queue.add(new Task(priority,s));//adds a new task to the priority queue	
	}
	
	//prints the next task in the queue based on its priority position
	private String next() {
		String s = "There are no tasks in the list"; //in case nothing is found
		Iterator<Task> iterator = queue.iterator(); //the iterator to scan the queue
		Task t; //temp variable - The task being looked at through each iterator
		Task target = new Task(); //Have to initialize in case it isnt found
		int lowest = 10; //the max priority is 9
		int taskPriority; //temporary variable to make code easier to read
		while (iterator.hasNext()) { //while something is in the queue
			t=iterator.next(); //store the next task in the queue into the temp variable
			taskPriority = t.getPriority(); //look at the priority number
			if (taskPriority<lowest) { //if its the lowest (or only) task,
				target=t;
				lowest=taskPriority;//set the task being examined as the target
			}
		}
		if (target.getDescription()!=null) { //if we found a task in the queue
			s=target.getDescription(); //set s to the desription to be returned
			boolean b = queue.remove(target); //Returns a boolean, so storing it in b
		}
		return s; 
	}
}

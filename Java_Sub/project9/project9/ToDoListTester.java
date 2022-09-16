package project9;
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

//The main client to demonstrate the classes
public class ToDoListTester {
	
	private static Scanner input;
	private static ToDoList list;

	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		list = new ToDoList();
		
		
		System.out.println("To Do List - Please enter an option");
		System.out.println("add priority description (add a new task)");
		System.out.println("next (remove and print most urgent task)");
		System.out.println("quit (exit this program)");
		
		String s;
		boolean flag=true;
		while (flag) {
			System.out.print(">");
			s = input.nextLine();
			if (!s.equalsIgnoreCase("quit")) {
				list.processString(s);
			}
			else
				flag=false;
		}
		System.out.println("Quitting Loop. Clearing the list... Executing additional tests:");
		list = new ToDoList();
		System.out.println("Adding the following 6 items to the list.");
		System.out.println("\"add 1 Complete Programming Exercise 15.11\"");
		System.out.println("\"add 8 Read for tomorrow's class\"");
		System.out.println("\"add 3 Soccer practice\"");
		System.out.println("\"add 6 Call parents\"");
		System.out.println("\"add 5 Have dinner with friends\"");
		System.out.println("\"add 9 Sleep well\"");
		list.addTask("add 1 Complete Programming Exercise 15.11");
		list.addTask("add 8 Read for tomorrow's class");
		list.addTask("add 3 Soccer practice");
		list.addTask("add 6 Call parents");
		list.addTask("add 5 Have dinner with friends");
		list.addTask("add 9 Sleep well");
		System.out.println();
		System.out.println("Entering 'add bad command'");
		list.addTask("add bad command");
		System.out.println("Expected: The priority must be an integer between 1 and 9.");
		System.out.println();
		System.out.println("Pulling most urgent items out.");
		list.nextTask();
		System.out.println("Expected: Complete Programming Exercise 15.11");
		list.nextTask();
		System.out.println("Expected: Soccer practice");
		list.nextTask();
		System.out.println("Expected: Have dinner with friends");
		list.nextTask();
		System.out.println("Expected: Call parents");
		list.nextTask();
		System.out.println("Expected: Read for tomorrow's class");
		list.nextTask();
		System.out.println("Expected: Sleep well");
		list.nextTask();
		System.out.println("Expected: There are no tasks in the list.");
		System.out.println();
		System.out.println("Performing tests on hashCode and equals methods...\n");
		System.out.println("Creating new task object with description 'Nothing'");
		Task task1 = new Task(1,"Nothing");
		System.out.println("Creating a 2nd task object with description 'Nothing'");
		Task task2 = new Task(2,"Nothing");
		System.out.println("Testing equals");
		System.out.println("Expected: Same");
		if (task1.equals(task2))
			System.out.println("Same");
		else
			System.out.println("Not the same");
		
		System.out.println("Hashcode of task1: "+task1.hashCode());
		System.out.println("Hashcode of task2: "+task2.hashCode());
		System.out.println("\nCreating another new task object with description 'Something'");
		Task task3 = new Task(2,"Something");
		System.out.println("Testing task1.equals(task3)");
		System.out.println("Expected: Not the same");
		if (task1.equals(task3))
			System.out.println("Same");
		else
			System.out.println("Not the same");
		
		System.out.println("Hashcode of task1: "+task1.hashCode());
		System.out.println("Hashcode of task3: "+task3.hashCode());
		
		input.close();


	}

}

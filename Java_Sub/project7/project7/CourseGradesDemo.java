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

//Client code
//Demonstrates the framework by using examples
public class CourseGradesDemo {

	//main function
	public static void main(String[] args) {
		
		
		//part 1
		System.out.println("Demonstrating part 1...");
		System.out.println("Creating an essay object and using .setScore(25,18,20,25)\n");
		Essay termPaper = new Essay();
		termPaper.setScore(25.0,18.0,20.0,25.0);
		System.out.print(termPaper);
		System.out.println();
		
		
		//part 2
		System.out.println("Demonstrating part 2...");
		System.out.println("Creating an object for the lab grade and setting the lab score to 85");
		GradedActivity lab = new GradedActivity(85);
		lab.setName("Lab");
		System.out.println("Creating an object for the pass/fail exam.");
		GradedActivity passfail = new PassFailExam(20,3,70.0);
		System.out.println("Creating an object for the essay grade and setting the essay scores\n");
		GradedActivity e = new Essay(25,18,17,20);
		GradedActivity exam = new FinalExam(50,10);
		System.out.println(lab);
		System.out.println(passfail);
		System.out.println(e);
		System.out.println(exam);
		System.out.println();
		
		//part 3
		System.out.println("Demonstrating part 3...");
		System.out.println("Creating a CourseGrades object...");
		System.out.println("Setting lab, passfail, essay, and final..");
		System.out.println("Demonstrating toString:");
		System.out.println();
		CourseGrades grades = new CourseGrades();
		grades.setLab(lab);
		grades.setPassFailExam(passfail);
		grades.setEssay(e);
		grades.setFinalExam(exam);
		System.out.print(grades);
	}

}

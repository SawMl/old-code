/*
 * Shawn Anthony
 * Taxes Program
 * Calculates state and federal tax based on salary, marital status, and state
 */

import java.util.Scanner;


//main class
public class taxes {
	
	
	/*Function for calculating Federal Tax based on salary and marital status
	 * married = true if married
	 */
	
	public static double calculateFederalTax(double income, boolean married) {
		double tax=0.10;
		if (!married) {
			if (income > 32000.0) {
				tax = (income - 32000.0) * 0.25 + 4400.0;
			}
			else if (income > 8000.0) {
				tax = (income - 8000.0) * 0.15 + 800.0;
			}
			else {
				tax=0.10*income;
			}
		}
		else {
			if (income > 64000.0) {
				tax = (income - 64000.0) * 0.25 + 8800.0;
			}
			else if (income > 16000.0) {
				tax = (income - 16000.0) * 0.15 + 1600.0;
			}
			else {
				tax=0.10*income;
			}
		}
		return tax;
	}
	
	//main function
	public static void main(String[] args) {
		
		//First, ask how many employees are in the company
		System.out.println("How many employees are in the company?");
		Scanner input = new Scanner(System.in);
		int numEmployees = input.nextInt();
		//check for valid input
		while (numEmployees<1) {
			System.out.println(numEmployees + " is not valid input");
			System.out.println("How many employees are in the company?");
			numEmployees = input.nextInt();
		}
		
		/*initialize variables
		 * Married or single is binary, so boolean data type
		 * salary could include cents, so double data type
		 * taxrates are represented as a fraction in my program, so double
		 */
		
		String name="";
		double salary=0.0;
		boolean married=false;
		String status = "";
		String state ="";
		double stateTaxRate=0.0;
		double federalTaxAmt = 0.0;
		double stateTaxAmt=0.0;
		double netSalary=0.0;
		
		/*
		 * execute loop for each employee in the company. 
		 * Find out the name, salary, marital status for each employee
		 * process and output the data before the next employee is processed
		*/
		
		/*
		 * Create an array of employees so that we can remember the data
		 * Use the stored data to output a table at the end
		 */
		
		
		employee[] e = new employee[numEmployees];

		
		for (int i=0;i<numEmployees;i++) {
			
			//construct the new employee object for each individual employee
			e[i] = new employee();
			
			System.out.println("What is the name of employee " + (i+1) + "?");
			
			/*
			 * Had a problem with input.nextLine() here. Seemed to skip the first one.
			 * Maybe because input.nextInt() above doesn't clear to the next input buffer
			 * Seems to work with 2 input.nextLines.
			 */
			
			name = input.nextLine();
			name = input.nextLine();
			
			e[i].setName(name);
			System.out.println("Input " + name + "'s salary:");
			salary = input.nextDouble();
			e[i].setSalary(salary);
			System.out.println("What is " + name + "'s marital status? Type single or married:");
			status = input.next();
			status=status.toUpperCase();
			
			//check for valid input
			while (!status.equals("MARRIED") && !status.equals("SINGLE")) {
				System.out.println(status + " is not valid input");
				System.out.println("What is " + name + "'s marital status? Type single or married:");
				status = input.next();
				status=status.toUpperCase();
			}
			if (status.equals("MARRIED")){
				married=true;
				e[i].setAsMarried();
			}
			else if (status.equals("SINGLE")) {
				married=false;
				e[i].setAsSingle();
			}
			
			System.out.println("Which state does " + name + " live in? Use 2 letter abbreviations:");
			state = input.next();
			
			//check for valid input
			while (state.length()!=2) {
				System.out.println(state + " is not valid input");
				System.out.println("Which state does " + name + " live in? Use 2 letter abbreviations:");
				state=input.next();
			}
			
			state = state.toUpperCase();
			if (state.equals("CA") || state.equals("NV") || state.equals("AZ") || state.equals("TX")){
				stateTaxRate=0.10;
			}
			else {
				stateTaxRate=0.12;
			}
			stateTaxAmt=salary*stateTaxRate;
			federalTaxAmt=calculateFederalTax(salary, married);
			netSalary=salary-stateTaxAmt-federalTaxAmt;
			
			System.out.print(name + "'s net salary is: $");
			System.out.printf("%.2f",netSalary);
			System.out.println();
			System.out.print("Federal: $"); 
			System.out.printf("%.2f",federalTaxAmt);
			System.out.println();
			System.out.printf("%10s","State: $"); 
			System.out.printf("%.2f", stateTaxAmt);
			System.out.println();
			System.out.println();
			
			e[i].setStateAmt(stateTaxAmt);
			e[i].setFederalAmt(federalTaxAmt);
		}
		
		//create table output
		System.out.println();
		
		for (int i=0;i<numEmployees;i++) {
			System.out.println(e[i].getName());
			System.out.print("Salary: $");
			System.out.printf("%.2f",e[i].getSalary());
			System.out.println();
			System.out.print("Federal Tax: -$");
			System.out.printf("%.2f",e[i].getFederal());
			System.out.println();
			System.out.print("State Tax: -$"); 
			System.out.printf("%.2f",e[i].getState());
			System.out.println();
			System.out.print("Net Salary: $");
			System.out.printf("%.2f",e[i].getNetSalary());
			System.out.println();
			System.out.println();
		}
	}
}

class employee {
	
	private String name;
	private double salary;
	private double federalAmt;
	private double stateAmt;
	private boolean married;
	private String state;
	
	 employee() {
		name="chuck";
		salary=0;
		federalAmt=0;
		stateAmt=0;
		married=false;
		state="";
	}
	
	public void setName(String n) {
		name=n;
	}
	public void setAsSingle() {
		married=false;
	}
	
	public void setState(String abb) {
		state=abb;
	}
	
	public void setAsMarried() {
		married=true;
	}
	
	public void setSalary(double x) {
		salary=x;
	}
	
	public void setFederalAmt(double amt) {
		federalAmt=amt;
	}
	
	public void setStateAmt(double amt) {
		stateAmt=amt;
	}
	
	public double getNetSalary() {
		return (salary-federalAmt-stateAmt);
	}
	
	public String getName() {
		return name;
	}
	
	public double getFederal() {
		return federalAmt;
	}
	
	public double getState() {
		return stateAmt;
	}
	
	public double getSalary() {
		return salary;
	}
}

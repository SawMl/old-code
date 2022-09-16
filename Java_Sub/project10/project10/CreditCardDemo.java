package project10;
import java.util.Scanner;

/**
 * @author Shawn Anthony
 * CECS 277 - Project 10
 * November 15, 2021
 * 
 * This program demonstrates use of the factory method design pattern
 * There are 3 different versions of a Credit Card:
 * Visa, Discover, and American Express
 * A factory design is responsible for the creation of new credit card objects
 */

public class CreditCardDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardFactory visa = new VisaFactory(100000,0);
		CardFactory discover = new DiscoverFactory(50000,75);
		CardFactory american = new AmericanExpressFactory(500000,100);
		
		CreditCard card;
		
		System.out.println("  Welcome to Banking  ");
		System.out.println("======================\n");
		
		boolean flag=true;
		Scanner input = new Scanner(System.in);
		String s;
		
		while (flag) {
			System.out.println("\n American Express");
			System.out.println(" Visa");
			System.out.println(" Discover");
			System.out.println(" Quit");
			System.out.println();
			System.out.print("Enter the card type you would like to visit:");
			s = input.nextLine();
			System.out.println();
			if (s.equalsIgnoreCase("Quit")) {
				flag=false;
			}
			else if (s.equalsIgnoreCase("American Express")) {
				card = american.getCreditCard();
				printCardInfo(card);
			}
			else if (s.equalsIgnoreCase("Visa")) {
				card = visa.getCreditCard();
				printCardInfo(card);
			}
			else if (s.equalsIgnoreCase("Discover")) {
				card = discover.getCreditCard();
				printCardInfo(card);
			}
			else {
				System.out.println("Invalid selection. Try again");
			}
			
		}
		System.out.println("Exiting program... Good Bye");
		input.close();
		System.exit(0);
	}
	
	private static void printCardInfo(CreditCard c) {
		System.out.println("Your card details are below:");
		System.out.println();
		System.out.println("Card type: "+c.cardType());
		System.out.println("Credit limit: $"+c.creditLimit());
		System.out.println("Annual charge $: "+c.annualCharge());
	}

}

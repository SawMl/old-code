package project10;
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

//base level abstract class CreditCard
public abstract class CreditCard {

	//3 abstract methods to implement
	
	//returns the type of card as a String
	public abstract String cardType();
	
	//returns the credit limit as int
	public abstract int creditLimit();
	
	//returns the annualCharge as int
	public abstract int annualCharge();
}

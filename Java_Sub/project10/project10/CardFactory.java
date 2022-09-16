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

//Base level abstract class for CardFactory
public abstract class CardFactory {
	
	//One single abstract method to be implemented
	public abstract CreditCard getCreditCard();
}

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

//One of the three children class to extend CardFactory
public class AmericanExpressFactory extends CardFactory {

	//private fields for initializing American Express Credit Cards
	private int creditLimit;
	private int annualCharge;
	
	//Overloaded constructor for initializing credit card production
	public AmericanExpressFactory(int creditLimit, int annualCharge) {
		super();
		this.creditLimit = creditLimit;
		this.annualCharge = annualCharge;
	}
	//the implemented abstract method
	//creates a new American Express credit card
	@Override
	public CreditCard getCreditCard() {
		return new AmericanExpressCreditCard(creditLimit,annualCharge);
	}
}

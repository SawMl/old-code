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
//Discover Credit Card IS A Credit Card
public class DiscoverCreditCard extends CreditCard {
	//private fields specific to Discover credit cards
	private String cardType;
	private int creditLimit;
	private int annualCharge;
	
	//Overloaded constructor
	//Takes credit limit and annual charge
	//sets the card type to Discover
	public DiscoverCreditCard(int creditLimit, int annualCharge) {
		super();
		this.creditLimit = creditLimit;
		this.annualCharge = annualCharge;
		this.cardType = "Discover";
	}

	//Accessor card type
	@Override
	public String cardType() {
		return cardType;
	}

	//Accessor for credit limit
	@Override
	public int creditLimit() {
		return creditLimit;
	}

	//Accessor for annual charge
	@Override
	public int annualCharge() {
		return annualCharge;
	}
	

}

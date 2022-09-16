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

//Essay Subclass extends the base class GradedActivity
public class Essay extends GradedActivity {
	
	//private variable fields
	private double grammar; //out of 30 points
	private double spelling; //out of 20 points
	private double correctLength; //out of 20 points
	private double content; //out of 30 points
	
	//default constructor
	public Essay() {
		grammar=0;
		spelling=0;
		correctLength=0;
		content=0;
	}
	
	//overloaded constructor
	public Essay(double g,double s,double cL,double c) {
		grammar=g;
		spelling=s;
		correctLength=cL;
		content=c;
		double score = grammar+spelling+correctLength+content;
		super.setScore(score);
	}
	

	//secScore mutator method
	//Doesn't need override due to the paramaters being difference
	public void setScore(double gr, double sp, double len, double cnt) {
		grammar=gr;
		spelling=sp;
		correctLength=len;
		content=cnt;
		double score = grammar+spelling+correctLength+content;
		super.setScore(score);
	}
	
	//getScore() method overrides the super class getScore() and in turn calls it
	//from the super
	
	@Override
	public double getScore() {
		return super.getScore();
	}
	
	//accessor method for grammar
	public double getGrammar() {
		return grammar;
	}
	
	/**
	 * mutator method for grammar
	 * @param grammar points out of 30
	 */
	public void setGrammar(double grammar) {
		this.grammar = grammar;
	}
	
	//accessor method for spelling
	public double getSpelling() {
		return spelling;
	}
	
	/**
	 * Mutator method for spelling
	 * @param spelling points out of 20
	 */
	public void setSpelling(double spelling) {
		this.spelling = spelling;
	}
	
	//accessor method for correctLength
	public double getCorrectLength() {
		return correctLength;
	}
	
	/**
	 * Mutator method for correctLength
	 * @param correctLength points out of 20
	 */
	public void setCorrectLength(double correctLength) {
		this.correctLength = correctLength;
	}
	
	//accessor method for content
	public double getContent() {
		return content;
	}
	
	/**
	 * Mutator method for content
	 * @param content points out of 30
	 */
	public void setContent(double content) {
		this.content = content;
	}
	
	//toString method for outputting data
	public String toString() {
		String s = "Term paper:\n\tGrammar points: " + this.grammar + "\n";
		s+="\tSpelling points: "+this.spelling+"\n";
		s+="\tLength points: "+this.correctLength+"\n";
		s+="\tContent points: "+this.content+"\n";
		s+=String.format("Total points: %.2f",this.getScore());
		s+=" Grade: "+this.getGrade()+"\n";
		return s;
	}
	
	
	
	
}

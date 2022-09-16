package practice6;
/*
 * This program demonstrates use of inheritance and polymorphism
 * With a base class, Book
 * a subclass, Encyclopdia
 * and a main class, BookInformation
 */

//Encyclopedia is a subclass of Book
public class Encyclopedia extends Book{
	protected String edition;
	protected int numVolumes;
	
	//default constructor
	public Encyclopedia() {
		super();
		edition="";
		numVolumes=0;
	}
	
	//mutator
	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	//mutator
	public void setNumVolumes(int a) {
		this.numVolumes=a;
	}
	
	//accessor
	public String getEdition() {
		return edition;
	}
	
	//accessor
	public int getNumVolumes() {
		return numVolumes;
	}
	
	//override for Book.printInfo
	
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("   Edition: "+this.edition);
		System.out.println("   Number of Volumes: "+this.numVolumes);
	}
}

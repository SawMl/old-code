package practice6;
/*
 * This program demonstrates use of inheritance and polymorphism
 * With a base class, Book
 * a subclass, Encyclopdia
 * and a main class, BookInformation
 */
public class Book {
	protected String title;
	protected String author;
	protected String publisher;
	protected String publicationDate;
	
	public Book() {
		title="";
		author="";
		publisher="";
		publicationDate="";
	}
	
	//implement all the getters and setters
	public String getTitle(){
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public String publicationDate() {
		return publicationDate();
	}
	
	public void setTitle(String t) {
		this.title=t;
	}
	
	public void setAuthor(String a) {
		this.author=a;
		
	}
	
	public void setPublisher(String publish) {
		this.publisher=publish;
	}
	
	public void setPublicationDate(String date) {
		this.publicationDate=date;
	}
	
	
	public void printInfo() {
		System.out.println("Book Information:");
		System.out.println("   Book Title: "+this.title);
		System.out.println("   Author: "+this.author);
		System.out.println("   Publisher: "+this.publisher);
		System.out.println("   Publication Date: "+this.publicationDate);
	}
	
	
}

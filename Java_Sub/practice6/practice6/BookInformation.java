package practice6;

/*
 * This program demonstrates use of inheritance and polymorphism
 * With a base class, Book
 * a subclass, Encyclopdia
 * and a main class, BookInformation
 */

public class BookInformation {
	public static void main(String[] args) {
		
		//Test the base class
		Book a = new Book();
		a.setTitle("The Hobbit");
		a.setAuthor("J. R. R. Tolkien");
		a.setPublisher("George Allen & Unwin");
		a.setPublicationDate("21 September 1937");
		
		//Test the subclass
		Encyclopedia e = new Encyclopedia();
		e.setTitle("The Illustrated Encyclopedia of the Universe");
		e.setAuthor("James W. Guthrie");
		e.setPublisher("Watson-Guptill");
		e.setPublicationDate("2001");
		e.setEdition("2nd");
		e.setNumVolumes(1);
		a.printInfo();
		e.printInfo();
	}
}

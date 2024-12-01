import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {

	private Book book;

	@Before
	public void setUp() {
		book = new Book(0, "Placeholder");// values do literally nothing, its just so I can use the isvalid function
	}

	@Test
	public void testBookValid() {
		// valid tests
		assertTrue("100 is supposed to be a valid id", book.isValidId(100));
		assertTrue("295 is supposed to be a valid id", book.isValidId(295));
		assertTrue("999 is supposed to be a valid id", book.isValidId(999));

		// invalid tests
		assertFalse("99 is not supposed to be a valid id", book.isValidId(99));
		assertFalse("-4 is not supposed to be a valid id", book.isValidId(-4));
		assertFalse("1000 is not supposed to be a valid id", book.isValidId(1000));

	}
	
	/*this one is confusing to me, as it asks to use a try catch block on addBook, 
	but there is no isValidId check in addBook, and I'm not sure if I'm supposed to modify the other classes*/
	@Test
	public void addbookInvalidId() {
		LibraryManagement lm = new LibraryManagement();
		Scanner scanner = new Scanner(System.in) ;
		
		System.out.println("id:");
		int id = scanner.nextInt();
		scanner.nextLine(); //eats the leftover newline
		System.out.println("title:");
		String title = scanner.nextLine();
		
		try {
			Book book = new Book(id,title);
			if (!book.isValidId(id))
				throw new IllegalArgumentException("book ID out of range: ID needs to be between 100 and 999");
			lm.library.addBook(book);
			fail("that ID was valid, put an invalid one next time");
		} catch (IllegalArgumentException e){
			System.out.println("error handled successfully:" + e.getMessage());
			scanner.close();
		}
		scanner.close();
	}
}

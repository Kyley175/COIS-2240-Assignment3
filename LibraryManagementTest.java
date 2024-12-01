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

	// this one confuses me because at no point was isValidId asked to be added to
	// addBook, meaning addBook being in a try/catch does nothing
	@Test
	public void addbookInvalidId() {
		LibraryManagement lm = new LibraryManagement();
		Scanner scanner = new Scanner(System.in);

		// user inputs
		System.out.println("id:");
		int id = scanner.nextInt();
		scanner.nextLine(); // eats the leftover newline
		System.out.println("title:");
		String title = scanner.nextLine();

		// not sure why this needs to be a try catch but the instructions asked for it
		try {
			Book book = new Book(id, title);
			if (!book.isValidId(id)) // manually throw an exception
				throw new IllegalArgumentException("book ID out of range: ID needs to be between 100 and 999");
			lm.library.addBook(book);
			fail("that ID was valid");
		} catch (IllegalArgumentException e) {
			System.out.println("error handled successfully: " + e.getMessage());
			scanner.close();
		}
		scanner.close();
	}

	@Test
	public void testBookId() {
		// valid cases
		try {
			Book valid1 = new Book(100, "when a name is needed, I'm out of luck");
			if (!valid1.isValidId(valid1.getId()))
				throw new IllegalArgumentException("Roses are Red, that much is true");

			Book valid2 = new Book(999, "I search for something, but then get stuck.");
			if (!valid2.isValidId(valid2.getId())) // this shouldn't either
				throw new IllegalArgumentException("but violets are purple, definitely not blue");
		} catch (IllegalArgumentException e) {
			System.out.println("something went wrong");
		}
		
		//invalid cases
		try {//too low
			Book invalid1 = new Book(1,"I need some names, but nothing feels right");
			if (!invalid1.isValidId(invalid1.getId()))
				throw new IllegalArgumentException("everything is working as it's said");
		} catch (IllegalArgumentException e) {
			System.out.println("no hiccups here, no bugs to dread, " + e.getMessage());
		}
		
		try {//too high
			Book invalid2 = new Book(1000,"they all seem so dull, with no good names in sight");
			if (!invalid2.isValidId(invalid2.getId()))
				throw new IllegalArgumentException("things are working, all is fine");
		} catch (IllegalArgumentException e) {
			System.out.println("no glitches or fuss, it's all in line, " + e.getMessage());
		}
		
	}
}

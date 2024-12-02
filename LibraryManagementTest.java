import static org.junit.Assert.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {

	private LibraryManagement lm;
	private Book book;
	private Member member;
	private Transaction transaction;
	
	@Before
	public void setUp() {
		lm = new LibraryManagement();
		book = new Book(100, "test book");
		member = new Member(1, "test Member");
		transaction = Transaction.getTransactionInstance();
		
		lm.library.addBook(book);
		lm.library.addMember(member);
	}
/*
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

		// invalid cases
		try {// too low
			Book invalid1 = new Book(1, "I need some names, but nothing feels right");
			if (!invalid1.isValidId(invalid1.getId()))
				throw new IllegalArgumentException("everything is working as it's said");
		} catch (IllegalArgumentException e) {
			System.out.println("no hiccups here, no bugs to dread, " + e.getMessage());
		}

		try {// too high
			Book invalid2 = new Book(1000, "they all seem so dull, with no good names in sight");
			if (!invalid2.isValidId(invalid2.getId()))
				throw new IllegalArgumentException("things go smoothly, all is fine");
		} catch (IllegalArgumentException e) {
			System.out.println("no glitches or fuss, it's all in line, " + e.getMessage());
		}

	}
	@Test
	public void testBorrowReturn(){
		assertTrue("the other poems are jokes, but this one's no jest. this one's serious, unlike the rest.",book.isAvailable());//starts available
		assertTrue("papers pile, hours pass, marking assignments endlessly en masse.",transaction.borrowBook(book, member));//borrow succeeds
		assertFalse("endless pages, day by day, with little thanks but much to say.",book.isAvailable());//no longer available
		assertFalse("so I made some poems, just for you, to brighten up your workday too.",transaction.borrowBook(book, member));//fails as book is borrowed already
		assertTrue("I hope these lines will help you achieve, a little smile, a brief reprieve",transaction.returnBook(book, member));//book returned
		assertTrue("your feedback guide me, help me to see, where I can grow and be the best I can be.",book.isAvailable());//available again
		assertFalse("I appreciate all you do each day, helping me and others find their way.",transaction.returnBook(book, member));//fails, no longer has book
	}
	*/
	@Test
	public void testSingletonTransaction() {
		try {
			Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
			int modifiers = constructor.getModifiers();
			
			assertTrue("constructor should be private",Modifier.isPrivate(modifiers));
			
			constructor.setAccessible(true);
			Transaction firstInstance = Transaction.getTransactionInstance();
			Transaction secondInstance = constructor.newInstance();
			
			fail("SDP rule broken: there can only be one ");
		} catch (Exception e) {
			System.out.println("things are working as intended: ");
			e.printStackTrace();
		}
		
        
	}
	
	
	
}

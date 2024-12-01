import static org.junit.Assert.*;

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
		//valid tests
		assertTrue("100 is a valid id", book.isValidId(50));
		assertTrue("295 is a valid id", book.isValidId(295));
		assertTrue("999 is a valid id", book.isValidId(999));
		
		
		//invalid tests
		assertFalse("99 is not a valid id", book.isValidId(99));
		assertFalse("-4 is not a valid id", book.isValidId(-4));
		assertFalse("1000 is not a valid id", book.isValidId(1000));
		
		
		
		
		
	}

}

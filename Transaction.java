import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Transaction {

	// private declaration of instance
	private static Transaction transaction;

	// private constructor prevents external instantiation
	private Transaction() {
		if (transaction != null) {
			throw new IllegalStateException("cant create more than one instance");
		}
	}

	public static Transaction getTransactionInstance() {
		/*
		 * lazy initialization, which I read about while looking up singleton pattern.
		 * it's meant to save resources by delaying initialization until it's needed,
		 * which makes no difference in this context but it's apparently good practice
		 * when it comes to singletons so tried it out
		 */
		if (transaction == null) {
			transaction = new Transaction();
		}

		return transaction;
	}

	// Perform the borrowing of a book
	public boolean borrowBook(Book book, Member member) {
		if (book.isAvailable()) {
			member.borrowBook(book); // I made it so member borrow then calls book borrow for persistent borrowing
										// purposes

			String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed "
					+ book.getTitle();
			System.out.println(transactionDetails);
			saveTransaction(transactionDetails);

			return true;
		} else {
			System.out.println("The book is not available.");

			return false;
		}
	}

	// Perform the returning of a book
	public boolean returnBook(Book book, Member member) {
		if (member.getBorrowedBooks().contains(book)) {
			member.returnBook(book);
			book.returnBook();

			String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned "
					+ book.getTitle();
			System.out.println(transactionDetails);
			saveTransaction(transactionDetails);
			return true;
		} else {
			System.out.println("This book was not borrowed by the member.");
			return false;
		}
	}

	// Get the current date and time in a readable format
	private static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	// add a transaction to the list
	public void saveTransaction(String details) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction.txt", true))) { // the true argument
																									// makes it so
			writer.write(details + "\n"); // need to swap to a new line after each entry
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error saving transaction: " + e.getMessage());
		}
	}

	// print out the list of past transactions
	public void displayTransactionHistory() {
		// for printing purposes
		String line;

		try (BufferedReader reader = new BufferedReader(new FileReader("transaction.txt"))) {
			System.out.println("\n--- Transaction History ---");
			if ("transaction.txt".length() == 0) {
				System.out.println("no transactions found(file empty)");

				return;
			}
			// prepares variable for printing and checks for the end of the file at the same
			// time! convenient!
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Error reading transaction history: " + e.getMessage());
		}
	}

}
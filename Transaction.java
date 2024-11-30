import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Transaction {
	
	//private declaration of instance
	private static Transaction transaction;
	
	
	//private constructor prevents external instantiation
	private Transaction() {
		
	}
	
	public static Transaction getTransactionInstance() {
		/*lazy initialization, which I read about while looking up singleton pattern. 
		it's meant to save resources by delaying initialization until it's needed, 
		which makes no difference in this context but it's apparently good practice when it comes to singletons so  tried it out*/
		if (transaction == null) {
			transaction = new Transaction();
		}
		return transaction;
	}
	
	

    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    

	//add a transaction to the list
	public void saveTransaction(String details) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction.txt", true))){
			writer.write(details + "\n"); //need to swap to a new line after each entry
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error saving transaction: " + e.getMessage());
		}
		
	}
	
	//print out the list of past transactions
	public void displayTransactionHistory() {
		//for printing purposes
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader("transaction.txt"))){
		 System.out.println("\n--- Transaction History ---");
		 if ("transaction.txt".length() == 0) {
			 System.out.println("no transactions found(file empty)");
			 return;
		 }
	        	//prepares variable for printing and checks for the end of the file at the same time! convenient!
	        	while ((line = reader.readLine()) != null) {
	        		System.out.println(line);
	        	}
		} catch (IOException e){
			System.out.println("Error reading transaction history: " + e.getMessage());
		}
	}
    
}
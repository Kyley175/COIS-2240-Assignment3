import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Transaction {
	
	//
	private static Transaction transaction;
	
	//private list declaration because if i also instantiate at the same time it gives an error for some reason
	private List<String> transactionHistory;
	
	//private constructor prevents external instantiation
	private Transaction() {
		//instantiation of list of past transactions
		transactionHistory = new ArrayList<>();
	
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
            addTransaction(transactionDetails);
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
            addTransaction(transactionDetails);
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
	public void addTransaction(String details) {
		transactionHistory.add(details);
	}
	
	//print out the list of past transactions
	public void displayTransactionHistory() {
		 System.out.println("\n--- Transaction History ---");
	        if (transactionHistory.isEmpty()) {
	            System.out.println("No transactions recorded.");
	        } else {
	            for (String transaction : transactionHistory) {
	                System.out.println(transaction);
	            }
	        }
	}
    
}
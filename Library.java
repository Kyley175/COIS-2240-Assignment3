import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

public class Library {
	//hash set values can't repeat, perfect for IDs
	private Set<Integer> bookIDs = new HashSet<Integer>();
	private Set<Integer> memberIDs = new HashSet<Integer>();
	private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();
    
    
    
    
    
    // Add a new member to the library
    public boolean addMember(Member member) {
        if (memberIDs.contains(member.getId())) {
        	System.out.println("error: Member ID already exists");
        	return false;
        }
    	members.add(member);
    	memberIDs.add(member.getId());
 		return true;
    }
    
    
    
    // Add a new book to the library
    public boolean addBook(Book book) {
    	if(bookIDs.contains(book.getId())) {
        	System.out.println("error: book ID already exists");
        	return false;
    	}
    	books.add(book);
    	bookIDs.add(book.getId());
    	return true;
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
    
  //persistent books and members, because it makes sense and I wanted to 
    // saves the data in the lists to files for future use
    public void saveLibraryData() {
    	try(BufferedWriter bookWriter = new BufferedWriter(new FileWriter("books.txt")); 
    		BufferedWriter memberWriter = new BufferedWriter(new FileWriter("members.txt"))){
    		
    		for (Book book:books) {
    			bookWriter.write(book.saveString() + "\n");
    		}
    		for (Member member: members) {
    			memberWriter.write(member.getId() + ", " + member.getName()+ "\n");
    		}
    		
    	} catch(IOException e) {
    		System.out.println("error saving library data: " + e.getMessage());
    	}
    }
    
    //reads saved data and adds it to the lists
    public void loadLibraryData() {
        try (BufferedReader bookReader = new BufferedReader(new FileReader("books.txt"));
             BufferedReader memberReader = new BufferedReader(new FileReader("members.txt"))) {
        	
        	String line;
        	
        	while ((line = memberReader.readLine()) != null) {
        		String[] memberData = line.split(","); //splits each line into id and name
        		int memberID = Integer.parseInt(memberData[0]);
        		String title = memberData[1];
        		members.add(new Member(memberID, title));
        		memberIDs.add(memberID);
        	}
        	
        	while ((line = bookReader.readLine()) != null) {
        		String[] bookData = line.split(","); //splits each line into id and name
        		int bookID = Integer.parseInt(bookData[0]);
        		String title = bookData[1];
        		boolean isAvailable = Boolean.parseBoolean(bookData[2]);
        		Integer borrowedBy = bookData[3].equals("null") ? null : Integer.parseInt(bookData[3].strip());
        		
        		Book book = new Book(bookID, title);
        		if (isAvailable == false) {
        			Member borrower = findMemberById(borrowedBy);
        			borrower.borrowBook(book);		
     
        		}
        		books.add(book);
        		bookIDs.add(bookID);
        	}
        
        } catch(IOException e) {
        	System.out.println("error loading library data: " + e.getMessage());
        }
    }
}

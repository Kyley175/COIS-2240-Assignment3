public class Book {
    private int id;
    private String title;
    private boolean available;
    private Integer borrowedBy; //for persistent borrowing purposes. use Integer for null
    
    public String saveString() {
    	return id + "," + title + "," + available + "," + (borrowedBy != null ? borrowedBy : "null"); //if/else can also be written with ?/:
    }
    
    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
        this.borrowedBy = null;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    // Method to borrow the book. modified for persistent borrowing
    public void borrowBook(Member member) {
        if (isAvailable()) {
            available = false;
            borrowedBy = member.getId();
        } else {
        	System.out.println("book already borrowed");
        }
    }

    // Method to return the book. modified for persistent borrowing
    public void returnBook() {
        available = true;
        borrowedBy = null;
    }
    
    

    // Method to check if a book id is valid
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }
}
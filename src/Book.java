import java.util.ArrayList;
import java.util.List;
public class Book 
{
    private String bookID, title, author, genre;
    private int publicationYear, Quantity;
    private int availableCopies;

    public Book()
    {
        
    }
    public Book(String bookID, String title, String author, String genre, int publicationYear, int Quantity) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.Quantity = Quantity;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    
    

    public void addBook()
    {
        
    }
    
    public void updateBook()
    {
        
    }
    
    public String displayBook()
    {
        return "No available Books";
    }
    
    public List<String> searchBook(String keyword)
    {
        List<String> Book = new ArrayList<>();
        Book.add( 0 , "The Sons Of Sparda" );
        
        return Book;
    }
    
    public Boolean isAvailable()
    {
        return true;
    }
        
}

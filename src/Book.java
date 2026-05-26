import java.util.ArrayList;
import java.util.List;
public class Book extends BorrowingTransaction
{
    private String bookID, title, author, genre;
    private int publicationYear, Quantity;
    
    //relationships picture attribute missing
    private int availableCopies;

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

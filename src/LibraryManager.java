import java.util.List;
import java.util.ArrayList;

public class LibraryManager
{
    // call methods from list
    private TransactionList TL = new TransactionList();
    private MemberList ML = new MemberList();
    private BookList BL = new BookList();
    
    public void addBook()
    {
        BL.add();
    }
    
    public void registerMember() 
    {
        ML.add();
    }
    
    public void borrowBook() 
    {
       TL.borrowBook();
    }
    
    public void returnBook()
    {
        TL.returnbook();
    }
    
    public void generateOverdueReport()
    {
        
    }
    
    public void saveData()
    {
        
    }
    
    public void loadData()
    {
        
    }
    
}

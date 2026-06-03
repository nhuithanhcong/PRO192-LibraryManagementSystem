import java.util.List;
import java.util.ArrayList;

public class LibraryManager
{
    private List<Book> bookList = new ArrayList<>(); 
    private List<Member> memberList = new ArrayList<>(); 
    private List<BorrowingTransaction> transactionList = new ArrayList<>();
    
    public void addBook(Book book) 
    {
        bookList.add(book);
    }
    
    public void registerMember(Member member) 
    {
        memberList.add(member);
    }
    
    public void borrowBook(String memberID, String bookID) 
    {
        // Logic tìm member, tìm book, kiểm tra điều kiện mượn ở đây
    }
    
    public void returnBook(String transactionID)
    {
        
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

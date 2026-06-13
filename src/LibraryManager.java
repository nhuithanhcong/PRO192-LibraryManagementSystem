import java.util.List;
import java.util.ArrayList;

public class LibraryManager
{
    // call methods from list
    private TransactionList TL = new TransactionList();
    private MemberList ML = new MemberList();
    private BookList BL = new BookList();

    
    public LibraryManager() 
    {
        
    }
    
    
    //~~~~~~~~~~~~~~~~~~~~~RETURN_MENU~~~~~~~~~~~~~~~~~~~~~//
    public void backToMenu()
    {
       return;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~MANAGE___BOOK~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void addBook()
    {
        BL.add();
    } 
    
    public void updateBook()
    {
        BL.update();
    }
    
    public void removeBook()
    {
        BL.delete();
    }
    
    public void viewAllBook()
    {
        BL.display();
    }
    
    public void searchBookByTerms()
    {
        BL.search();
    }
     
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~MANAGE___MEMBERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void addNewMember()
    {
        ML.add();
    }
    
    public void updateMemberInfo()
    {
        ML.update();
    }
    
    public void removeMember()
    {
        ML.delete();
    }
    
    public void viewAllMember()
    {
        ML.display();
    }
    
      public void searchMemberByIdOrName()
    {
        ML.search();
    }
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~BORROWING___RETURNING____BOOK~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void borrowBook() 
    {
       TL.borrowBook();
    }
    
    public void returnBook()
    {
        TL.returnbook();
    }
    
    public void viewBorrowedBooks()
    {
        TL.displayBorrowedBooks();
    }
    
    public void viewBorrowingHistoryOfSpecificMember()
    {
       TL.displayBorrowingHistory();
    }
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~REPORTS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~// 
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void viewCurrentlyBorrowedBooks()
    {
        
    }
    
    public void viewOverdueBooks()
    {
        
    }
    
    public void viewMostPopularBooks()
    {
        
    }
    
    public void viewMemberWithTheMostBorrowing()
    {
        
    }
    
}

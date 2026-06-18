import java.util.List;
import java.util.ArrayList;

public class LibraryManager
{
    // call methods from list
    private TransactionList TL;
    private MemberList ML;
    private BookList BL;
    
    //INITIALIZING AND RETRIEVING DATA FROM EXISTING CLASSES 
    public LibraryManager() 
    {
        this.ML = new MemberList();
        this.BL = new BookList();
        this.TL = new TransactionList();
        
        this.TL.setML(this.ML);
        this.TL.setBL(this.BL);
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
    public void viewCurrentlyBorrowedBooks() // --> ???
    {
        TL.displayBorrowedBooks(); 
    }
    
    public void viewOverdueBooks()
    {
        TL.viewOverdueBooks();
    }
    
    public void viewMostPopularBooks()
    {
        TL.viewMostPopularBooks();
    }
    
    public void viewMemberWithTheMostBorrowing()
    {
        TL.viewMemberWithTheMostBorrowing();
    }
    
}


import java.io.FileNotFoundException;
import java.io.IOException;
public class LibraryManager
{
    // call methods from list
    private TransactionList TL;
    private MemberList ML;
    private BookList BL;
    private File_IO file;
    
    //INITIALIZING AND RETRIEVING DATA FROM EXISTING CLASSES 
    public LibraryManager() throws FileNotFoundException 
    {
        this.ML = new MemberList();
        this.BL = new BookList();
        this.TL = new TransactionList();
        this.file = new File_IO();
        this.TL.setML(this.ML);
        this.TL.setBL(this.BL);
        
        this.file.setBL(this.BL);
        this.file.setML(this.ML);
        this.file.setTL(this.TL);
        //chi them loadfile 1 lan de LM load data cu ve la duoc
        file.loadFileForBook();
        file.loadFileForMember();
        file.loadFileForTransaction();
        
        
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~MANAGE___BOOK~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void addBook() throws IOException
    {
        BL.add();
        file.createFileforBook();
    } 
    
    public void updateBook() throws IOException
    {
        BL.update();
        file.createFileforBook();
    }
    
    public void removeBook() throws IOException
    {
        BL.delete();
        file.createFileforBook();
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
    public void addNewMember() throws IOException
    {
        ML.add();
        file.createFileforUser();
    }
    
    public void updateMemberInfo() throws IOException
    {
        ML.update();
        file.createFileforUser();
    }
    
    public void removeMember() throws IOException
    {
        ML.delete();
        file.createFileforUser();
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
    public void borrowBook() throws IOException 
    {
       int oldSize = TL.size();
       TL.borrowBook();
       if (TL.size() > oldSize) {//dat dieu kien de tranh truong hop luu TL k hop le
           file.createFileforBook();
           file.createFileforUser();
           file.createFileforBorrowingBook();
       }
    }
    
    public void returnBook() throws IOException
    {
        TL.returnbook();
           file.createFileforBook();
           file.createFileforUser();
           file.createFileforBorrowingBook();
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
import java.io.FileWriter;
import java.io.IOException;
public class File_IO 
{
    private TransactionList TL;
    private MemberList ML;
    private BookList BL;
    //CREATING FILE 

    public void setTL(TransactionList TL) {
        this.TL = TL;
    }

    public void setML(MemberList ML) {
        this.ML = ML;
    }

    public void setBL(BookList BL) {
        this.BL = BL;
    }
    
    public void test()
    {
        System.out.println( "TL size is:" + TL.size() );
    }
    public void createFileforBook() throws IOException
    {
            FileWriter obj = new FileWriter("BookList.txt");  
    }
    public void createFileforUser() throws IOException
    {
            FileWriter obj = new FileWriter("UserList.txt");     
    }
    public void createFileforBorrowingBook() throws IOException
    {
            FileWriter obj= new FileWriter("TransactionList.txt");  
    }
    public void createFileforReturningBook() throws IOException
    {
            FileWriter obj= new FileWriter("ReturningBook.txt");  
    }
    //WRITING FILE
    
    //READING FILE
     
    
}
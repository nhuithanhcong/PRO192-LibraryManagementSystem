import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BorrowingTransaction 
{
    private String transactionID;
    private LocalDateTime borrowDate,dueDate,returnDate;
    private  double fineAmount;
    private String Status; // Borrowed/Returned
    
    public void borrowBook()
    {
        
    }
    public void returnBook()
    {
        
    }
    public double calculateFine()
    {
        return 9000;
    }
}

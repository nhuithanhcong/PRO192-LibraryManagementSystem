import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Member 
{
    private String memberID, name, phone, email, address;
    
    //localDate
    private LocalDateTime localDate;
    private DateTimeFormatter dateformatter;
    
    
    //Methods
    public void registerMember()
    {
        
    }
    public void updateMember()
    {
        
    }
    public String displayMember()
    {
        return "No available Members";
    }
           
    //Methods not in abstraction
    public String getMember()
    {
        return memberID;
    }
    
    public void updateinfo()
    {
        
    }
    
    public int getBorrowLimit()
    {
        return 0;
    }
    
    public double calculateFine(int OverdueDays)
    {
        return 696969696969.696969;
    }
     
    
}

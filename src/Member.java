import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Member
{
    private String memberID, name, phone, email;
    
    //localDate
    private LocalDateTime localDate;
    private DateTimeFormatter dateformatter;
    
    public Member() {
    }
    
   

    public Member(String memberID, String name, String phone, String email) {
        this.memberID = memberID;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
public String toString() {
    return String.format(
        "%-8s %-20s %-15s %-25s",
        memberID,
        name,
        phone,
        email
    );
}

    
    
    
  
    
    public int getBorrowLimit()
    {
        return 0;
    }
    
    public double calculateFine(int OverdueDays)
    {
        return 0;
    }
     
    
}

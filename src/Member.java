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
    private int currentAmountOfBorrowing;
    private int amountOfBorrowingForMember;
    
    public Member() {
    }

    public Member(String memberID, String name, String phone, String email, int currentAmountOfBorrowing, int amountOfBorrowingForMember) {
        this.memberID = memberID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.currentAmountOfBorrowing = currentAmountOfBorrowing;
        this.amountOfBorrowingForMember = amountOfBorrowingForMember;
    }

    public int getAmountOfBorrowingForMember() {//so lan member muon sach
        return amountOfBorrowingForMember;
    }

    public void setAmountOfBorrowingForMember(int amountOfBorrowingForMember) {
        this.amountOfBorrowingForMember = amountOfBorrowingForMember;
    }

    

    public int getCurrentAmountOfBorrowing() {//con bao nhieu luot muon nua cua member
        return currentAmountOfBorrowing;
    }

    public void setCurrentAmountOfBorrowing(int currentAmountOfBorrowing) {
        this.currentAmountOfBorrowing = currentAmountOfBorrowing;
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

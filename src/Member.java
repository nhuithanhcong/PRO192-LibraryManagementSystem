import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Member extends ArrayList<Member>
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

  
    
    
    
    //Methods
    public void addMember()
    {   
        Member member = new Member();
        Scanner newMember = new Scanner(System.in);
        String memberInfor; 
         System.out.println("Your ID: ");
         memberInfor = newMember.nextLine();
        System.out.println("Your name: ");
         memberInfor = newMember.nextLine();
        System.out.println("Your phone: ");
         memberInfor = newMember.nextLine();
        System.out.println("Your mail");
         memberInfor = newMember.nextLine();
       
        
    }
    public void updateMember()
    {
        
    }
    public String displayMember()
    {
        return "No available Members";
    }
    public String searchMember(String name) {
        
        return null;
        
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

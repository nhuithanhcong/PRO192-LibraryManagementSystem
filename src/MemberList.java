
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class MemberList extends ArrayList<Member>{
    
    
    
    //input du lieu tu user
    public Member inputNewMember() {

    Scanner sc = new Scanner(System.in);//tao scanner de lay input tu 

    System.out.println("----------- ADD MEMBER -----------");

    System.out.print("Member ID: ");
    String id = sc.nextLine();

    System.out.print("Name: ");
    String name = sc.nextLine();

    System.out.print("Phone: ");
    String phone = sc.nextLine();

    System.out.print("Email: ");
     String email = sc.nextLine();

    return new Member(id, name, phone, email);
}   
    //them new member vao arraylist
     public void addMember(Member newMember)
    {   
        this.add(newMember);
        System.out.println("New member added successfully!");
    }
     
     //Display member
     public void displayMember()
    {
        int size = this.size();
        for (int i = 0; i < size; i++) {
            Member member = this.get(i);
            System.out.println(member.toString());
        }
    }
    
    
    public void updateMember()
    {
        
    }
    
    public String searchMember(String name) {
        
        return null;
        
    }
           
    //Methods not in abstraction
    public String getMember()
    {
        return Member;
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

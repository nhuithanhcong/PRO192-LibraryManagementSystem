import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


public class MemberList extends ArrayList<Member> implements/*lay chuc nang chung cua general*/ GeneralUtil
{
   
            
    //input du lieu tu user
    @Override
    public void add() {

    Scanner sc = new Scanner(System.in);//tao scanner de lay input tu user

    System.out.println("----------- ADD MEMBER -----------");

    /*System.out.print("Member ID: ");
    String id = sc.nextLine();
    if (isDuplicateID(id)) {
        System.out.println("Member ID already exists!");
        return;
    }*/
    String id = Utility.generateID(this, "member");
    System.out.println("Generated Member ID: " + id);
    
    System.out.print("Name: ");
    String name = sc.nextLine();

    System.out.print("Phone Number: ");
    String phone = sc.nextLine();
    if (phone.length() != 10) {
        System.out.println("your phone number must have 10 digits!");
        return;
    }

    System.out.print("Email: ");
    String email = sc.nextLine();
    
    System.out.println("Select your member type: ");
    System.out.println("[1] Regular member    [2] Premium member");
    int type = sc.nextInt();
    Member newMember;// phai khai bao newMember o ngoai trc vi khi dua vao if else se chi tinh member trong {} -> khi ra ngoai if else ta k the this.add member vi member k ton tai
    if(type == 1) {
        newMember = new RegularMember(id, name, phone, email);
    } else if (type == 2) {
        newMember = new PremiumMember(id, name, phone, email);
    } else {
        System.out.println("Invalid member type!");
        return;
    }
    
    System.out.println("[1] Save    [2] Cancel");
    System.out.print("Choose: ");
    int choice = sc.nextInt();
    sc.nextLine();
    if (choice == 1) {
        this.add(newMember);//them new member vao class arraylist
        System.out.println("Member added successfully!");//tao ra object de gan scan member moi vao class member
        
    } else {
        System.out.println("Operation cancelled!");
    } 
}   

    
     
     
     
     //Display member
    @Override
    public void display() {

        if (this.isEmpty()) {
        System.out.println("No available members!");
        return;
    }

    System.out.println("\n----------- MEMBER LIST -----------");

    System.out.printf(
        "%-8s %-20s %-15s %-25s\n",
        "ID",
        "Name",
        "Phone",
        "Email"
    );

    System.out.println("------------------------------------------------------------------");
    
    for (Member member : this) {
        System.out.println(member);
    }

    System.out.println("------------------------------------------------------------------");
}
    
    
    
    @Override
    public void update()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- UPDATE MEMBER -----------");
        System.out.println("Enter member ID: ");
        String updateID = sc.nextLine();
        
        
        int size = this.size();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            Member member = this.get(i);
            if (member.getMemberID().equalsIgnoreCase(updateID)) {
            found = true;
            System.out.println("Current Information: " + member.toString());
            

            System.out.print("New Name: ");//nhap input gia tri moi vao
            String newName = sc.nextLine();//gan gia tri moi vao member 
            member.setName(newName);// va tu member do se gan vao memberList moi nho chuhc nang set

            System.out.print("New Phone Number: ");
            String newPhoneNumber = sc.nextLine();
            member.setPhone(newPhoneNumber);

            System.out.print("Email: ");
            String newEmail = sc.nextLine();
            member.setEmail(newEmail);                                                               
            System.out.println("Member updated successfully!");
            break;//dung vonglap lun de k con phai chay them 1 vong moi nua de tiet kiem tai nguyen va thoi gian
            } 
        }
        if (!found) {
            System.out.println("Member not found.");
         
        }
    }
    
    
    
    @Override
    public void search() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- SEARCH MEMBER -----------");
        System.out.println("Enter member name: ");
        String searchName = sc.nextLine();
        
        boolean found = false;
        int size = this.size();
        for (int i = 0; i < size; i++) {
            Member member = this.get(i);
            if (member.getName().equalsIgnoreCase(searchName)) {
                found = true;
                System.out.println("Member found: ");
                System.out.println(member.toString());
                break;       
                
            }
        }
        if (!found) {
            System.out.println("Member not found.");
        } 
    }
    
   
    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- DELETE MEMBER -----------");
        System.out.println("Enter member name: ");
        String searchName = sc.nextLine();
        
        Member removeMember = null;//tao object removeMember = null de khi ma tim thay id cua member thi se gan object nay vao member dc tim thay
        
        int size = this.size();
        for (int i = 0; i < size; i++) {
            Member member = this.get(i);
            if (member.getName().equalsIgnoreCase(searchName)) {
                removeMember = member;// neu da tim dc member thanh cong thi se gan member dc tim thay do vao object removeMember de dinh danh member do de de hon trong vjec delete member
                System.out.println("Member found: ");
                System.out.println(member.toString());
                break;        
            }
        }
        if (removeMember != null) {
            System.out.println("[1] Delete   [2] Cancel");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            
            if (choice == 1) {
                this.remove(removeMember);//Tu 2 note tren ta co the thay rang trong muc deleteMember can co 1 bien co de xac dinh member do la ai de co the remove de hon
                System.out.println("Member deleted successfully!");
            } else {
                System.out.println("Operation cancelled!");
            } 
        } else {
            System.out.println("Member not found.");
        }
    }
     //Ham ktra xem lieu id cua member dinh input da ton tai hay chua      
    public boolean isDuplicateID(String id) {
    int size = this.size();
    for (int i = 0; i < size; i++) {
        Member member = this.get(i);
        if (member.getMemberID().equalsIgnoreCase(id)) {

            return true;
        }
    }

    return false;
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
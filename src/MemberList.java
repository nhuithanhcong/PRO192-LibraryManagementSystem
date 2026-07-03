import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

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
    String id = Utility.generateIDvTest(this, "member");
    System.out.println("Generated Member ID: " + id);
    
    System.out.print("Name: ");
    String name = sc.nextLine();

    System.out.print("Phone Number: ");
    String phone = sc.nextLine();
    if (!Utility.isValidPhoneNumber(phone)) return;
    
    System.out.print("Email: ");
    String email = sc.nextLine();
    
    System.out.println("Select your member type: ");
    System.out.println("[1] Regular member    [2] Premium member");
    System.out.print("choose: ");
    int type = sc.nextInt();
    Member newMember;// phai khai bao newMember o ngoai trc vi khi dua vao if else se chi tinh member trong {} -> khi ra ngoai if else ta k the this.add member vi member k ton tai
    if(type == 1) {
        newMember = new RegularMember(id, name, phone, email,"Regular", 3, 0);
    } else if (type == 2) {
        newMember = new PremiumMember(id, name, phone, email, "Premium", 5, 0);
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
        "%-8s %-20s %-15s %-25s %-60s\n",
        "ID",
        "Name",
        "Phone",
        "Email",
        "Status"
    );

    System.out.println("-----------------------------------------------------------------------------------");
    
    for (Member member : this) {
        System.out.println(member.toString());
    }

    System.out.println("-----------------------------------------------------------------------------------");
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
        System.out.println("1. Enter member name ");
        System.out.println("2. Enter member id");
        System.out.print("Choose: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.print("Enter member name: ");
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
        } else if (choice == 2) {
            System.out.print("Enter number ID: ");
            String searchID = sc.nextLine();
            boolean found = false;
            for (int i = 0; i < this.size(); i++) {
                Member member = this.get(i);
                if (member.getMemberID().equalsIgnoreCase(searchID)) {
                    found = true;
                    System.out.println("Member found: ");
                    System.out.println(member.toString());
                    break;
                }
            }
            if (!found) {
                System.out.println("Member not found.");
            }
        } else{
            System.out.println("invalid choice!");
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
                if (removeMember.getCurrentAmountOfBorrowing() != 0) {
                    System.out.println("This person still currently borrowing a book");
                }else {
                    this.remove(removeMember);//Tu 2 note tren ta co the thay rang trong muc deleteMember can co 1 bien co de xac dinh member do la ai de co the remove de hon
                    System.out.println("Member deleted successfully!");
                }
                
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
    
}

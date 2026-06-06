
import java.util.ArrayList;
import java.util.Scanner;


public class MemberList extends ArrayList<Member>{
    
    
    
    //input du lieu tu user
    public void addNewMember() {

    Scanner sc = new Scanner(System.in);//tao scanner de lay input tu user

    System.out.println("----------- ADD MEMBER -----------");

    System.out.print("Member ID: ");
    String id = sc.nextLine();

    System.out.print("Name: ");
    String name = sc.nextLine();

    System.out.print("Phone Number: ");
    String phone = sc.nextLine();

    System.out.print("Email: ");
    String email = sc.nextLine();
    
    System.out.println("[1] Save    [2] Cancel");
    System.out.print("Choose: ");
    int choice = sc.nextInt();
    if (choice == 1) {
        System.out.println("Member added successfully!");
        Member newMember = new Member(id, name, phone, email);//tao ra object vao member moi
        this.add(newMember);//them new member vao arraylist
    } else {
        System.out.println("Operation cancelled!");
    } 
}   

    
     
     
     
     //Display member
     public void displayMember()
    {
        int size = this.size();
        if (size == 0) {
            System.out.println("No available member.");
            return;
        }
        for (int i = 0; i < size; i++) {
            Member member = this.get(i);
            System.out.println(member.toString());
        }
    }
    
    
    
    public void updateMember()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- UPDATE MEMBER -----------");
        System.out.println("Enter member ID: ");
        String updateID = sc.nextLine();
        
        boolean found = false;
        int size = this.size();
        for (int i = 0; i < size; i++) {
            Member member = this.get(i);
            if (member.getMemberID().equalsIgnoreCase(updateID)) {
            found = true;
            System.out.println("Current Information: " + member.toString());
            
            
            System.out.print("New Member ID: ");
            String newID = sc.nextLine();//nhap input gia tri moi vao
            member.setMemberID(newID);//gan gia tri moi vao member va tu member do se gan vao memberList moi

            System.out.print("New Name: ");
            String newName = sc.nextLine();
            member.setMemberID(newName);

            System.out.print("New Phone Number: ");
            String newPhoneNumber = sc.nextLine();
            member.setMemberID(newPhoneNumber);

            System.out.print("Email: ");
            String newEmail = sc.nextLine();
            member.setMemberID(newEmail);
            
            break;//dung vonglap lun de k con phai chay them 1 vong moi nua de tiet kiem tai nguyen va thoi gian
            }
            
        }
        if (!found) {
            System.out.println("Member not found.");
        }
    }
    
    
    
    public void searchMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- SEARCH MEMBER -----------");
        System.out.println("Enter member ID: ");
        String searchID = sc.nextLine();
        
        boolean found = false;
        int size = this.size();
        for (int i = 0; i < size; i++) {
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
    }
    
    public void deleteMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- DELETE MEMBER -----------");
        System.out.println("Enter member ID: ");
        String searchID = sc.nextLine();
        
        Member removeMember = null;//tao object removeMember = null de khi ma tim thay id cua member thi se gan object nay vao member dc tim thay
        
        int size = this.size();
        for (int i = 0; i < size; i++) {
            Member member = this.get(i);
            if (member.getMemberID().equalsIgnoreCase(searchID)) {
                removeMember = member;// neu da tim dc member thanh cong thi se gan member dc tim thay do vao object removeMember de dinh danh member do de de hon trong vjec delete member
                System.out.println("Member found: ");
                System.out.println(member.toString());
                break;        
            }
        }
        if (removeMember != null) {
            System.out.println("[1] Save    [2] Cancel");
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
           
    
    
    public int getBorrowLimit()
    {
        return 0;
    }
    
    public double calculateFine(int OverdueDays)
    {
        return 696969696969.696969;
    }
    
    
    
}

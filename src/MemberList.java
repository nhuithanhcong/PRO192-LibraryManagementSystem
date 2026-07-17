import java.util.ArrayList;
import java.util.Scanner;

public class MemberList extends ArrayList<Member> implements GeneralUtil {

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- ADD MEMBER -----------");

        String id = Utility.generateIDvTest(this, "member");
        System.out.println("Generated Member ID: " + id);
        
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine();
        if (!Utility.isValidPhoneNumber(phone)) {
            System.out.println("Invalid Phone Number! Operation stopped.");
            return;
        }
        
        System.out.print("Email: ");
        String email = sc.nextLine();
        if (!Utility.isValidEmail(email)) {
            System.out.println("Invalid Email! Operation stopped.");
            return;
        }
        
        System.out.println("Select your member type: ");
        int type = Utility.tryCatchInt(sc, "[1] Regular member    [2] Premium member: ");
        
        Member newMember;
        if (type == 1) {
            newMember = new RegularMember(id, name, phone, email, "Regular", 3, 0);
        } else if (type == 2) {
            newMember = new PremiumMember(id, name, phone, email, "Premium", 5, 0);
        } else {
            System.out.println("Invalid member type! Operation stopped.");
            return;
        }
        
        System.out.println("[1] Save    [2] Cancel");
        int choice = Utility.tryCatchInt(sc, "Choose action: ");
        
        if (choice == 1) {
            this.add(newMember); // Thêm thành viên mới vào danh sách
            System.out.println("Member added successfully!");
        } else {
            System.out.println("Operation cancelled!");
        } 
    }   

    @Override
    public void display() {
        if (this.isEmpty()) {
            System.out.println("No available members!");
            return;
        }

        System.out.println("\n----------- MEMBER LIST -----------");
        System.out.printf(
            "%-8s %-20s %-15s %-25s %-60s\n",
            "ID", "Name", "Phone", "Email", "Status"
        );
        System.out.println("-----------------------------------------------------------------------------------");
        
        for (Member member : this) {
            System.out.println(member.toString());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }
    
    // --- 3. CHỨC NĂNG CẬP NHẬT (UPDATE) ---
    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- UPDATE MEMBER -----------");
        System.out.print("Enter member ID: ");
        String updateID = sc.nextLine();
        
        Member member = null;
        for (Member m : this) {
            if (m.getMemberID().equalsIgnoreCase(updateID)) {
                member = m;
                break;
            }
        }
        
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        
        System.out.println("Current Information: " + member.toString());

        System.out.print("New Name: ");
        String newName = sc.nextLine().trim();

        System.out.print("New Phone Number: ");
        String newPhoneNumber = sc.nextLine().trim();
        if (!newPhoneNumber.isEmpty() && !Utility.isValidPhoneNumber(newPhoneNumber)) {
            System.out.println("Update failed! Invalid Phone Number format.");
        }

        System.out.print("New Email: ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty() && !Utility.isValidEmail(newEmail)) {
            System.out.println("Update failed! Invalid Email format.");
        }

        if (!newName.isEmpty()) member.setName(newName);
        if (!newPhoneNumber.isEmpty()) member.setPhone(newPhoneNumber);
        if (!newEmail.isEmpty()) member.setEmail(newEmail);

        System.out.println("Member updated successfully!");
    }
    
   
    @Override
    public void search() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- SEARCH MEMBER -----------");
        System.out.println("1. Enter member name ");
        System.out.println("2. Enter member ID");
        
        int choice = Utility.tryCatchInt(sc, "Choose search option (1 or 2): ");
        
        if (choice == 1) {
            System.out.print("Enter member name: ");
            String searchName = sc.nextLine();
        
            boolean found = false;
            for (Member member : this) {
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
            System.out.print("Enter member ID: ");
            String searchID = sc.nextLine();
            boolean found = false;
            for (Member member : this) {
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
        } else {
            System.out.println("Invalid choice!");
        }
    }
    
    
    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------- DELETE MEMBER -----------");
        System.out.print("Enter member name: ");
        String searchName = sc.nextLine();
        
        Member removeMember = null;
        for (Member member : this) {
            if (member.getName().equalsIgnoreCase(searchName)) {
                removeMember = member;
                System.out.println("Member found: ");
                System.out.println(member.toString());
                break;        
            }
        }
        
        if (removeMember != null) {
            System.out.println("[1] Delete   [2] Cancel");
            int choice = Utility.tryCatchInt(sc, "Choose action: ");
            
            if (choice == 1) {
                if (removeMember.getCurrentAmountOfBorrowing() != removeMember.getBorrowLimit()) {
                    System.out.println("This person is still currently borrowing a book! Cannot delete.");
                } else {
                    this.remove(removeMember);
                    System.out.println("Member deleted successfully!");
                }
            } else {
                System.out.println("Operation cancelled!");
            } 
        } else {
            System.out.println("Member not found.");
        }
    }
}
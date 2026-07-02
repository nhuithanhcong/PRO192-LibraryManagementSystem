
public class Member
{
    private String memberID, name, phone, email, status;
    private int currentAmountOfBorrowing;
    private int amountOfBorrowingForMember;
    
    public Member() {
    }

    public Member(String memberID, String name, String phone, String email, String status, int currentAmountOfBorrowing, int amountOfBorrowingForMember) {
        this.memberID = memberID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.currentAmountOfBorrowing = currentAmountOfBorrowing;
        this.amountOfBorrowingForMember = amountOfBorrowingForMember;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentAmountOfBorrowing() {
        return currentAmountOfBorrowing;
    }

    public void setCurrentAmountOfBorrowing(int currentAmountOfBorrowing) {
        this.currentAmountOfBorrowing = currentAmountOfBorrowing;
    }

    public int getAmountOfBorrowingForMember() {
        return amountOfBorrowingForMember;
    }

    public void setAmountOfBorrowingForMember(int amountOfBorrowingForMember) {
        this.amountOfBorrowingForMember = amountOfBorrowingForMember;
    }
    
   

    @Override
public String toString() {
    return String.format(
        "%-8s %-20s %-15s %-25s %-15s",
        memberID,
        name,
        phone,
        email,
        status
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
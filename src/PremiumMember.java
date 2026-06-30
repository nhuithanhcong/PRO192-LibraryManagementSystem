public class PremiumMember extends Member
{

    public PremiumMember() {
    }

    public PremiumMember(String memberID, String name, String phone, String email, String status, int currentAmountOfBorrowing, int amountOfBorrowingForMember) {
        super(memberID, name, phone, email, status, currentAmountOfBorrowing, amountOfBorrowingForMember);
    }

    
    
    

    

    @Override
    public int getBorrowLimit() {
        return 5;
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 3000; 
    }

    
    
    
    
}

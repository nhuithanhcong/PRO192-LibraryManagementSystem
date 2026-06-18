public class PremiumMember extends Member
{
    
    public PremiumMember() {
    }

    public PremiumMember(String memberID, String name, String phone, String email) {
        super(memberID, name, phone, email);
    }

    @Override
    public int getBorrowLimit() {
        return 5;
    }

    @Override
    public double calculateFine(int OverdueDays) {
        return OverdueDays * 3000; 
    }

    
    
    
    
}

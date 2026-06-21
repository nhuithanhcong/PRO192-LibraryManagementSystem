public class RegularMember extends Member
{

    public RegularMember() {
    }

    public RegularMember(String memberID, String name, String phone, String email, int currentAmountOfBorrowing, int amountOfBorrowingForMember) {
        super(memberID, name, phone, email, currentAmountOfBorrowing, amountOfBorrowingForMember);
    }
   
   

    

    @Override
    public int getBorrowLimit() {
        return 3;
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 5000;
    }

    

    
    
    
    
    
}

public class RegularMember extends Member
{
    private int borrowlimit;
    private double fineRatePerDay;
    
    public int getBorrowLimit()
    {
        return 0;
    }
    public double calculateFine(int overdueDays)
    {
        return 0.1;
    }
    
}

public class BorrowingTransaction 
{
    private String transactionID;
    private String borrowDate,dueDate,returnDate;
    private String Status; // Borrowed/Returned
    private String memberID;//  borrow va return can phai biet thong tin thanh vien
    private String bookID; // borrow va return can phai biet thong tin book
    private double fineAmount;
    private int currentAmountOfBorrowing;
    private int AmountOfBorrowingForBooks;
    private int AmountOfBorrowingForMembers;
 

    public BorrowingTransaction()
    {
         
    }

    public BorrowingTransaction(String transactionID, String borrowDate, String dueDate, String returnDate, String Status, String memberID, String bookID, double fineAmount, int currentAmountOfBorrowing, int AmountOfBorrowingForBooks, int AmountOfBorrowingForMembers) {
        this.transactionID = transactionID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.Status = Status;
        this.memberID = memberID;
        this.bookID = bookID;
        this.fineAmount = fineAmount;
        this.currentAmountOfBorrowing = currentAmountOfBorrowing;
        this.AmountOfBorrowingForBooks = AmountOfBorrowingForBooks;
        this.AmountOfBorrowingForMembers = AmountOfBorrowingForMembers;
    }

    public int getCurrentAmountOfBorrowing() {
        return currentAmountOfBorrowing;
    }

    public void setCurrentAmountOfBorrowing(int currentAmountOfBorrowing) {
        this.currentAmountOfBorrowing = currentAmountOfBorrowing;
    }

    public int getAmountOfBorrowingForBooks() {
        return AmountOfBorrowingForBooks;
    }

    public void setAmountOfBorrowingForBooks(int AmountOfBorrowingForBooks) {
        this.AmountOfBorrowingForBooks = AmountOfBorrowingForBooks;
    }

    public int getAmountOfBorrowingForMembers() {
        return AmountOfBorrowingForMembers;
    }

    public void setAmountOfBorrowingForMembers(int AmountOfBorrowingForMembers) {
        this.AmountOfBorrowingForMembers = AmountOfBorrowingForMembers;
    }


    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    @Override
public String toString() {
    return String.format(
        "%-8s %-20s %-20s %-20s %-10.2f %-12s %-10s %-10s",
        transactionID,
        borrowDate,
        dueDate,
        returnDate,
        fineAmount,
        Status,
        memberID,
        bookID
    );
}

    
    
         
    
    
    // methods
    public  void borrowBook()
    {
        
    }
    public void returnBook()
    {
        
    }
    public double calculateFine()
    {
        return 9000;
    }
}
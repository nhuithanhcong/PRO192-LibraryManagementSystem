public class BorrowingTransaction 
{
    private String transactionID;
    private String borrowDate,dueDate,returnDate;
    private  double fineAmount;
    private String Status; // Borrowed/Returned
    private String memberID;//  borrow va return can phai biet thong tin thanh vien
    private String bookID; // borrow va return can phai biet thong tin book

    public BorrowingTransaction()
    {
         
    }

    public BorrowingTransaction(String transactionID, String borrowDate, String dueDate, String returnDate, double fineAmount, String Status, String memberID, String bookID) {
        this.transactionID = transactionID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.Status = Status;
        this.memberID = memberID;
        this.bookID = bookID;
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
        return "BorrowingTransaction{" + "transactionID=" + transactionID + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", fineAmount=" + fineAmount + ", Status=" + Status + ", memberID=" + memberID + ", bookID=" + bookID + '}';
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

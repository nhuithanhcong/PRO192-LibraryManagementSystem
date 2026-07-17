import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.ZonedDateTime; // do mins, hours, day, month
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;

// 6 h toi chuyen ngay
// regular mem -> 1 tuan borrow
// premium mem -> 1 thang borrow
// 3 ngay sau khi qua han
public class TransactionList extends ArrayList<BorrowingTransaction>
{
    private MemberList ML;
    private BookList BL;
    private String userId = null;
    private String bookId = null;
    private String TransactionId = null;
    
    public void setML(MemberList ML) {
        this.ML = ML;
    }

    public void setBL(BookList BL) {
        this.BL = BL;
    }
    public boolean returnOrcontinue(String input)
    {
        if(input.equalsIgnoreCase("Esc")) 
        {
            return true;
        }
        else return false;
    }

    
    //////////////////////////                             //////////////////////////                              
    //////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//////////////////////////
    ///BORROW BOOK FUNCTION///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~///BORROW BOOK FUNCTION///
    //////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//////////////////////////
    //////////////////////////                             //////////////////////////
    public void borrowBook()
    {
        
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        
        /////////////////////////
        //User enters member ID//
        /////////////////////////
        

        System.out.print("Please Enter the user ID: ");
        userId = input.nextLine();
        System.out.print("Please Enter the Book ID: ");
        bookId = input.nextLine();
        ZonedDateTime DT = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")); // khai bao object date va time o ho chi minh, lay thoi gian ngay luc chay code nay
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // format custom
        String BorrowDateNTime = DT.format(format);
        System.out.println("Borrow Date (DD/MM/YYYY): " + BorrowDateNTime);
        System.out.println("[1] Confirm  [2] Cancel");
        System.out.println("Choose: ");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            if(userId.length() == 0 || userId.charAt(0) != 'M')
            {
                System.out.println("Please Enter The User ID correctly!");
                System.out.println("Press Enter to continue Or type Esc to return!");
                String e = input.nextLine();
                if(returnOrcontinue(e)) 
                {
                    return;
                }
            }

                    //////////////////////////////////////////////////////////////////////////////////////////
            // check if the member list is empty first before validaing the existence of the user ID//
            //////////////////////////////////////////////////////////////////////////////////////////

            //System.out.println("Size of ML is: " + ML.size()); // -> optional
            if(ML.isEmpty())
            {
                System.out.println("There are no Data in the member list database! (Press enter to return!)");
                input.nextLine();
                return;
            }

            ////////////////////////////////////////////////
            //Look for the member ID if it actually exist!//
            ////////////////////////////////////////////////
        
            for(int i = 0; i < ML.size(); i++)
            {
            Member member = ML.get(i);
                boolean check = userId.equalsIgnoreCase(member.getMemberID()); // check userId (input from user) and member.getMemberID() (id from the member list!)
                //check whether member exists
                if(check == true)
                {
                    //System.out.println("User ID Found!");
                    break;
                }
                else if(check == false && i == ML.size()-1)
                {
                    System.out.println("User ID Not Found In The DataBase!"); 
                    System.out.println("Press Enter to return!");
                    input.nextLine();
                    return;
                }
            }
            ///////////////////////
            //User enters Book ID//
            ///////////////////////

            while(true)
            {

                if(bookId.length() != 0 && bookId.charAt(0) == 'B')
                {
                    //System.out.println("book ID Entered Successfully!");
                    break;
                }
                else
                {
                    System.out.println("Please Enter The book ID correctly!");
                    System.out.println("Press Enter to continue Or type Esc to return!");
                    String e = input.nextLine();
                    if(returnOrcontinue(e)) 
                    {
                        return;
                    }
                }        
            }

            ////////////////////////////////////////////////////////////////////////////////////////
            // check if the book list is empty first before validaing the existence of the book ID//
            ////////////////////////////////////////////////////////////////////////////////////////

            //System.out.println("Size of BookList is: " + BL.size()); // -> optional
            if(BL.isEmpty())
            {
            System.out.println("There are no Data in the book list database! (Press enter to return!)");
                input.nextLine();
                return;
            }
                    ////////////////////////////////////////////////
            //Look for the Book ID if it actually exist!//
            ////////////////////////////////////////////////
            int save_pointB = 0;
            for(int i = 0; i < BL.size(); i++)
            {
            Book book = BL.get(i);
                boolean checkB = bookId.equalsIgnoreCase(book.getBookID()); // check bookId (input from user) and book.getBookID() (id from the book list!)
                //Check whether the book exists and is available.
                if(checkB == true) 
                {
                   save_pointB = i;
                   //System.out.println("The Book is exist!");
                   break;  // found book then break the loop no need to find anymore!
                }
                else if(checkB == false && i == BL.size()-1)
                {
                    System.out.println("Book Not Found In The DataBase!"); 
                    System.out.println("Press Enter to continue Or type Esc to return!");
                    String e = input.nextLine();
                    if(returnOrcontinue(e)) 
                    {
                        return;
                    }
                }
            }

            ////////////////////////////////////////////////
            //Now Check the quantity and reducing quantity//
            ////////////////////////////////////////////////

            int quantity = -9;
            for(int i = 0; i < BL.size(); i++)
            {
                Book book = BL.get(i);
                //System.out.println("Book Info: " + book.toString());
                boolean checkB = bookId.equalsIgnoreCase(book.getBookID()); // check bookId (input from user) and book.getBookID() (id from the book list!)
                quantity = book.getQuantity();
                //System.out.println("Quantity: " + quantity);
                if(quantity > 0 && checkB) 
                {
                    //System.out.println("quantity of the books is not 0!"); 
                    quantity--;    //Reduce book quantity and update the book quantity in the library
                    System.out.println("Reducing the quantity!"); 
                    book.setQuantity(quantity);
                }
                else if(quantity <= 0 && !checkB && i == BL.size()-1)
                {
                    System.out.println("quantity of the books is 0!");
                    System.out.println("Press enter to return!");
                    input.nextLine();
                    return;
                }
            }

            //////////////////////////////////////////////////////////////
            //Now Check the amount of borrowing + adding new transaction//
            //////////////////////////////////////////////////////////////


            for(int i = 0 ;i < ML.size();i++)
            {
            Member member = ML.get(i);
            Book book = BL.get(save_pointB);
                boolean check = userId.equalsIgnoreCase(member.getMemberID());
                if(check)
                {      
                    int CurrentAmountOfBorrowing = member.getCurrentAmountOfBorrowing(); // How many time one person can borrow a book left
                    int AmountOfBorrowingForMember = member.getAmountOfBorrowingForMember(); // how many time did this person borrowed a book

                    int AmountOfBorrowingForBooks = book.getAmountOfBorrowingForBook(); // how many time a book being borrowed
                    int CurrentborrowingAbook = book.getCurrentBorrowingBook(); // Is that book being borrowed
                    //System.out.println("CurrentAmount 0f Borrowing: " + CurrentAmountOfBorrowing);

                    if(CurrentAmountOfBorrowing > 0) 
                    {
                        //System.out.println("Borrowing Limit not exceeded!"); 
                        BorrowingTransaction newBT = new BorrowingTransaction();

                        CurrentAmountOfBorrowing--;
                        newBT.setCurrentAmountOfBorrowing(CurrentAmountOfBorrowing);
                        member.setCurrentAmountOfBorrowing(CurrentAmountOfBorrowing);

                        AmountOfBorrowingForMember++;
                        newBT.setAmountOfBorrowingForMembers(AmountOfBorrowingForMember);
                        member.setAmountOfBorrowingForMember(AmountOfBorrowingForMember);

                        AmountOfBorrowingForBooks++;
                        newBT.setAmountOfBorrowingForBooks(AmountOfBorrowingForBooks);
                        book.setAmountOfBorrowingForBook(AmountOfBorrowingForBooks);

                        book.setCurrentBorrowingBook(1); // 1 = borrowed, 0 = not borrowing
                        //now adding new information to the transaction object    

                           newBT.setTransactionID( Utility.generateIDvTest(this, "Transaction") );



                           if(member.getBorrowLimit() == 3) DT = DT.plusWeeks(1); // if regular -> DUE DATE = BORROWDATE + 1 tuan (OBJECTT)
                           else DT = DT.plusMonths(1); // else la premium DUE DATE = BORROWDATE + 1 thang (OBJECT)
                           String DueDateNTime = DT.format(format);

                           newBT.setBorrowDate(BorrowDateNTime); 
                           newBT.setDueDate(DueDateNTime); 
                           newBT.setFineAmount(0);
                           newBT.setReturnDate("Not Yet Settled");
                           newBT.setAmountOfBorrowingForMembers(AmountOfBorrowingForMember);
                           newBT.setStatus("Borrowed");
                           newBT.setMemberID(userId);
                           newBT.setBookID(bookId);
                           //Save object into ArrayList (Transaction List).
                           this.add(newBT);
                           //Display success message.
                           System.out.println("Book " + book.getTitle() + " borrowed by " + member.getName() + " succesfully");

                           //end
                           System.out.println("Press Enter to return!");
                           input.nextLine();

                           return; 
                    }
                }
            }
            } else {
                System.out.println("Cancelled.");
            }
            
            
            
            
        
        
        
        
        

    }
    
    //////////////////////////                             //////////////////////////
    //////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//////////////////////////
    ///RETURN BOOK FUNCTION///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~///RETURN BOOK FUNCTION///    
    //////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//////////////////////////
    //////////////////////////                             //////////////////////////
    
    
    public void returnbook()
    {
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        String returnDate = null;
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        // check if the Transaction list is empty first before validaing the existence of the transaction//
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        
        //System.out.println("Size of TL is: " + this.size()); // -> optional
        if(this.isEmpty())
        {
            System.out.println("There are no Data in the transaction list database! (Press enter to return!)");
            input.nextLine();
            return;
        }

        
        ///////////////////////////////
        //User enters Transaction ID //
        ///////////////////////////////
        
        
        System.out.print("Please Enter the Transaction ID: ");
        TransactionId = input.nextLine();
        System.out.print("Please Enter the user ID: ");
        userId = input.nextLine();
        System.out.print("Please Enter the Book ID: ");
        bookId = input.nextLine();
        ZonedDateTime DT = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        
        
        DT = DT.plusYears(1);
        
        
        DateTimeFormatter CustomFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String ReturnDateNTime = DT.format(CustomFormat);
        System.out.println("Return date (DD/MM/YYYY): " + ReturnDateNTime);
        System.out.println("[1] Confirm  [2] Cancel");
        System.out.print("Choose: ");
        int choice = 0;
        try{
            choice = input.nextInt();
            input.nextLine();
        }catch(InputMismatchException e)
        {
            System.out.println("Please enter a number!");
        }
        if (choice == 1) {
            if(TransactionId.length() == 0 && TransactionId.charAt(0) != 'T')
            {
            //System.out.println("TransactionID ID Entered Successfully!");
            System.out.println("Please Enter The Transaction ID correctly!");
            System.out.println("Press Enter to continue Or type Esc to return!");
            String e = input.nextLine();
            if(returnOrcontinue(e)) 
            {
                return;
            }
            }
            /////////////////////////////////////////////////////////////////////
            // check if the Transaction ID actually existed within the database//
            /////////////////////////////////////////////////////////////////////
            int save_indexForTransaction = -99;
            for(int i = 0; i < this.size(); i++)
            {
                if(TransactionId.equalsIgnoreCase(this.get(i).getTransactionID()))
                {
                    save_indexForTransaction = i;
                    //System.out.println("Transaction Found in the database!");
                    break;
                }
                else if(save_indexForTransaction == -99 && i == this.size()-1)
                {
                    System.out.println("The transaction does not exist in the database!");
                    System.out.println("Press Enter to return");
                    input.nextLine();
                    return;
                }
            }
            //////////////////////////////////////////////////////////////////////////////////////////
            // check if the member list is empty first before validaing the existence of the user ID//
            //////////////////////////////////////////////////////////////////////////////////////////

            //System.out.println("Size of ML is: " + ML.size()); // -> optional
            if(ML.isEmpty())
            {
                System.out.println("There are no Data in the member list database! (Press enter to return!)");
                input.nextLine();
                return;
            }
             /////////////////////////
            //User enters member ID//
            /////////////////////////

            while(true)
            {

                if(userId.length() != 0 && userId.charAt(0) == 'M')
                {
                    //System.out.println("User ID Entered Successfully!");
                    break;
                }
                else
                {
                    System.out.println("Please Enter The User ID correctly!");
                    System.out.println("Press Enter to continue Or type Esc to return!");
                    String e = input.nextLine();
                    if(returnOrcontinue(e)) 
                    {
                        return;
                    }
                }
            }

            ////////////////////////////////////////////////
            //Look for the member ID if it actually exist!//
            ////////////////////////////////////////////////
            int save_indexForMember = -99;
            for(int i = 0; i < ML.size(); i++)
            {
                Member member = ML.get(i);
                boolean check = userId.equalsIgnoreCase(member.getMemberID()); // check userId (input from user) and member.getMemberID() (id from the member list!)
                //check whether member exists
                if(check == true)
                {
                    save_indexForMember = i;
                    //System.out.println("User ID Found!");
                    break;
                }
                else if(check == false && i == ML.size()-1)
                {
                    System.out.println("User ID Not Found In The DataBase!"); 
                    System.out.println("Press Enter to return!");
                    input.nextLine();
                    return;
                }
            }    

            ////////////////////////////////////////////////////////////////////////////////////////
            // check if the book list is empty first before validaing the existence of the book ID//
            ////////////////////////////////////////////////////////////////////////////////////////

            //System.out.println("Size of BookList is: " + BL.size()); // -> optional
            if(BL.isEmpty())
            {
                System.out.println("There are no Data in the book list database! (Press enter to return!)");
                input.nextLine();
                return;
            }

            ///////////////////////
            //User enters Book ID//
            ///////////////////////

            while(true)
            {

                if(bookId.length() != 0 && bookId.charAt(0) == 'B')
                {
                    //System.out.println("book ID Entered Successfully!");
                    break;
                }
                else
                {  
                    System.out.println("Please Enter The book ID correctly!");
                    System.out.println("Press Enter to continue Or type Esc to return!");
                    String e = input.nextLine();
                    if(returnOrcontinue(e)) 
                    {
                        return;
                    }
                }        
            }
            ////////////////////////////////////////////////
            //Look for the Book ID if it actually exist!//
            ////////////////////////////////////////////////
            int save_indexForBook = -99;

            for(int i = 0; i < BL.size(); i++)
            {
                Book book = BL.get(i);
                boolean checkB = bookId.equalsIgnoreCase(book.getBookID()); // check bookId (input from user) and book.getBookID() (id from the book list!)
                //Check whether the book exists and is available.
                if(checkB == true) 
                {
                    save_indexForBook = i;
                   //System.out.println("The Book is exist and avaiable!");
                   break;  // found book then break the loop no need to find anymore!
                }
                else if(checkB == false && i == BL.size()-1)
                {
                    System.out.println("Book ID Not Found In The DataBase!"); 
                    System.out.println("Press Enter to continue Or type Esc to return!");
                    String e = input.nextLine();
                    if(returnOrcontinue(e)) 
                    {
                        return;
                    }
                }
            }

            ///////////////////////////////////////
            //Update the transaction Information!//
            ///////////////////////////////////////

            BorrowingTransaction currentBT = this.get(save_indexForTransaction);
            Member currentM = ML.get(save_indexForMember);
            Book currentB = BL.get(save_indexForBook);

            if(currentBT.getStatus().equalsIgnoreCase("Returned"))
            {
                System.out.println("The User Already Returned a book!");
                System.out.println("Press Enter to return!");
                input.nextLine();
                return;
            }
            //update info for currentBT
            //update ReturnDate info in BT
            currentBT.setReturnDate(ReturnDateNTime); 

            // Calculate Fine

            ///////////////////////////////////////////////////
            //Retrieve each parts of Date and Time
            String DueDateNTime = currentBT.getDueDate(); // vi du: 22/12/2022 12:23:19
            String[] DDNT = DueDateNTime.split(" "); // DDNT[0] = 22/12/2022 va DDNT[1] = 12:23:19\
            String[] DueDateS = DDNT[0].split("/"); // DueDateS[0] = 22; DueDateS[1] = 12; DueDateS[2] = 2022
            String[] DueTimeS = DDNT[1].split(":"); // ^^^^^^^^^

            String[] RDNT = ReturnDateNTime.split(" ");
            String[] ReturnDateS = RDNT[0].split("/");
            String[] ReturnTimeS = RDNT[1].split(":");


            //////////////////////////////////////////       
            //Turn String Date to Value Date
            int DueDays = Integer.parseInt(DueDateS[0]);
            int DueMonths = Integer.parseInt(DueDateS[1]);
            int DueYears = Integer.parseInt(DueDateS[2]);

            int ReturnDays = Integer.parseInt(ReturnDateS[0]);
            int ReturnMonths = Integer.parseInt(ReturnDateS[1]);
            int ReturnYears = Integer.parseInt(ReturnDateS[2]);

            /////////////////////////////////////////////////////
            //Compare DueDateNTIme with ReturnDateNTime
            LocalDate startDate = LocalDate.of(DueYears,DueMonths,DueDays); // parameter la int
            LocalDate endDate = LocalDate.of(ReturnYears, ReturnMonths, ReturnDays);
            int OverdueDays = (int) ChronoUnit.DAYS.between(startDate, endDate); 
           
            if(OverdueDays > 0)
            {
                currentBT.setFineAmount(currentM.calculateFine(OverdueDays));
                if(OverdueDays > 3 && currentM.getBorrowLimit() == 3)
                {
                    
                    currentBT.setStatus("OVERDUE [Regular]");
                    currentBT.setFineAmount(currentM.calculateFine(OverdueDays));
                }
                else if(OverdueDays >= 31  && currentM.getBorrowLimit() == 5){
                    currentBT.setStatus("OVERDUE [Premium]");
                    currentBT.setFineAmount(currentM.calculateFine(OverdueDays));
                } // 31 fixed value of a month
                else{
                    currentBT.setStatus("OVERDUE");
                    currentBT.setFineAmount(currentM.calculateFine(OverdueDays));
                }
                
                System.out.println("Book " + currentB.getTitle() + " returned by " + currentM.getName() + " Overdue day: " + OverdueDays + ". Overdue fine: " + currentBT.getFineAmount());
            } else {
                currentBT.setStatus("Returned");
                System.out.println("Book " + currentB.getTitle() + " returned by " + currentM.getName() + ". No overdue fine");
            }
            currentB.setQuantity(currentB.getQuantity() + 1);
            currentB.setCurrentBorrowingBook(0);
            currentM.setCurrentAmountOfBorrowing(currentM.getCurrentAmountOfBorrowing() + 1);
        } else {
            System.out.println("Cancelled.");
        }
    }
   
    public void displayBorrowedBooks()
    {
        if (this.isEmpty()) 
        {
            System.out.println("No transaction found!");
            return;
        }
    System.out.println("\n---------------- BORROWED BOOKS LIST ----------------");

    System.out.printf(
        "%-8s %-20s %-20s %-20s %-10s %-12s %-10s %-10s\n",
        "ID",
        "Borrow",
        "Due",
        "Return",
        "Fine",
        "Status",
        "Member",
        "Book"
    );

    System.out.println("-------------------------------------------------------------------------------------------");

    for (BorrowingTransaction transaction : this)  System.out.println(transaction.toString());

    System.out.println("-------------------------------------------------------------------------------------------");
        
        //Return to previous Menu
        Scanner input = new Scanner(System.in);
        System.out.println("Press Enter to continue Or type Esc to return!");
        String e = input.nextLine();
        if(returnOrcontinue(e)) 
        {
            return;
        } 
    }
    
    
    public void displayBorrowingHistory()
    {
        Scanner input = new Scanner(System.in);
        String userId = null;
        while(true)
        {
            System.out.print("Please Enter the user ID: ");
            userId = input.nextLine();
            if(userId.length() != 0)
            {
                System.out.println("User ID Entered Successfully!");
                break;
            }
            else
            {
                System.out.println("Please Enter The User ID correctly!");
                System.out.println("Press Enter to continue Or type Esc to return!");
                String e = input.nextLine();
                if(returnOrcontinue(e)) 
                {
                    return;
                }
            }
        }

        if(this.size() == 0)
        {
            System.out.println("there are no Data in the database!");
            System.out.println("Press Enter to return");
            String e = input.nextLine();
            return;
        }
        for(int i = 0; i < this.size();i++)
        {
            boolean check = this.get(i).getMemberID().equalsIgnoreCase(userId);
            if(check) 
            {
                System.out.println(this.get(i).toString()); 
                return;
            }
        }
    }
    
    public void viewOverdueBooks()
{
    boolean found = false;

    System.out.println("==============================================================================================================");
    System.out.println("                                         OVERDUE BOOKS REPORT");
    System.out.println("==============================================================================================================");

    System.out.printf("%-10s %-30s %-12s %-22s %-15s %-12s\n",
            "Book ID",
            "Title",
            "Member ID",
            "Member Name",
            "Due Date",
            "Status");

    System.out.println("--------------------------------------------------------------------------------------------------------------");

    for (BorrowingTransaction BT : this)
    {
        if (BT.getStatus().equalsIgnoreCase("OVERDUE")
            || BT.getStatus().equalsIgnoreCase("OVERDUE [3]"))
    {
        Book book = null;
        Member member = null;

        // Tim Book
        for (Book b : BL)
        {
            if (b.getBookID().equalsIgnoreCase(BT.getBookID()))
            {
                book = b;
                break;
            }
        }

        // Tim Member
        for (Member m : ML)
        {
            if (m.getMemberID().equalsIgnoreCase(BT.getMemberID()))
            {
                member = m;
                break;
            }
        }

        
        if (book != null && member != null)
        {
            System.out.printf("%-10s %-30s %-12s %-22s %-15s %-12s%n",
                    book.getBookID(),
                    book.getTitle(),
                    member.getMemberID(),
                    member.getName(),
                    BT.getDueDate(),
                    BT.getStatus());
            found = true;
        }
    }

    System.out.println("==============================================================================================================");

    if(!found)
    {
        System.out.println("No overdue books.");
    }
    }
}
    
    public void viewMostPopularBooks()
    {
        Scanner input = new Scanner(System.in);

        if(BL.isEmpty())
        {
            System.out.println("There are no books in the database!");
            input.nextLine();
            return;
        }

        System.out.println("===============================================================");
        System.out.println("                     MOST POPULAR BOOKS");
        System.out.println("===============================================================");

        System.out.printf("%-8s %-30s %-18s\n",
                "Book ID",
                "Title",
                "Borrow Times");

        System.out.println("---------------------------------------------------------------");

        int max = -1;

        for(Book book : BL)
        {
            if(book.getAmountOfBorrowingForBook() > max)
            {
                max = book.getAmountOfBorrowingForBook();
            }
        }

        for(Book book : BL)
        {
            if(book.getAmountOfBorrowingForBook() == max)
            {
                System.out.printf("%-8s %-30s %-18d\n",
                        book.getBookID(),
                        book.getTitle(),
                        book.getAmountOfBorrowingForBook());
            }
        }

        System.out.println("===============================================================");
    }
    public void viewMemberWithTheMostBorrowing() {
        Scanner input = new Scanner(System.in);

        if( ML.isEmpty())
        {
            System.out.println("There are no members in the database!");
            input.nextLine();
            return;
        }

        System.out.println("===============================================================");
        System.out.println("                     MEMBER WITH MOST BORROWING BOOKS");
        System.out.println("===============================================================");

        System.out.printf("%-8s %-30s %-18s\n",
                "Member ID",
                "Member name",
                "Borrow Times");

        System.out.println("---------------------------------------------------------------");

        int max = -1;

        for(Member member : ML)
        {
            if(member.getAmountOfBorrowingForMember() > max)
            {
                max = member.getAmountOfBorrowingForMember();
            }
        }

        for(Member member : ML)
        {
            if(member.getAmountOfBorrowingForMember() == max)
            {
                System.out.printf("%-8s %-30s %-18d\n",
                        member.getMemberID(),
                        member.getName(),
                        member.getAmountOfBorrowingForMember());
            }
        }

        System.out.println("===============================================================");
    }
}

  
    
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.ZonedDateTime; // do mins, hours, day, month
import java.time.format.DateTimeFormatter;

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
    public int ReturnMaxDaysOfMonths(int currentDay, int start, int end, int year)
    {
        int howManyDays = 0;
        boolean leapYear = (year % 4 == 0) && (year % 400 == 0);
        while(start != end && howManyDays >= 0)
        {
            if(howManyDays == 0)
            {
                if(start == 2 && leapYear) howManyDays = 29 - currentDay;
                else if(start == 2 && !leapYear) howManyDays = 28 - currentDay;
                else if(start == 4 || start == 6 || start == 9 || start == 11) howManyDays = 30 - currentDay;
                else howManyDays = 31 - currentDay;
            }
            if(start > 13) start = 1;
            if(start == 2 && leapYear) howManyDays += 29;
            else if(start == 2 && !leapYear) howManyDays += 28;
            else if(start == 4 || start == 6 || start == 9 || start == 11) howManyDays += 30;
            else howManyDays += 31;
            start++;
        }
        return howManyDays;
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
        
        while(true)
        {
            System.out.print("Please Enter the user ID: ");
            userId = input.nextLine();
            if(userId.length() != 0 && userId.charAt(0) == 'M')
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
        
        //////////////////////////////////////////////////////////////////////////////////////////
        // check if the member list is empty first before validaing the existence of the user ID//
        //////////////////////////////////////////////////////////////////////////////////////////
        
        System.out.println("Size of ML is: " + ML.size()); // -> optional
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
                System.out.println("User ID Found!");
                break;
            }
            else if(check == false && i == ML.size()-1)
            {
                System.out.println("User ID Not Found In The DataBase!"); 
                System.out.println("Press Enter to return!");
                String e = input.nextLine();
                return;
            }
        }    
        
        ///////////////////////
        //User enters Book ID//
        ///////////////////////
        
        while(true)
        {
            System.out.print("Please Enter the Book ID: ");
            bookId = input.nextLine();
            if(bookId.length() != 0 && bookId.charAt(0) == 'B')
            {
                System.out.println("book ID Entered Successfully!");
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
        
        System.out.println("Size of BookList is: " + BL.size()); // -> optional
        if(BL.isEmpty())
        {
System.out.println("There are no Data in the book list database! (Press enter to return!)");
            input.nextLine();
            return;
        }
        
        ////////////////////////////////////////////////
        //Look for the Book ID if it actually exist!//
        ////////////////////////////////////////////////
        
        for(int i = 0; i < BL.size(); i++)
        {
            Book book = BL.get(i);
            boolean checkB = bookId.equalsIgnoreCase(book.getBookID()); // check bookId (input from user) and book.getBookID() (id from the book list!)
            //Check whether the book exists and is available.
            if(checkB == true) 
            {
               System.out.println("The Book is exist!");
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
        
        int quantity;
        for(int i = 0; i < BL.size(); i++)
        {
            Book book = BL.get(i);
            boolean checkB = bookId.equalsIgnoreCase(book.getBookID()); // check bookId (input from user) and book.getBookID() (id from the book list!)
            quantity = book.getQuantity();
            
            if(quantity > 0 && checkB) 
            {
                System.out.println("Books are still available!"); 
                quantity--;    //Reduce book quantity and update the book quantity in the library
                System.out.println("Reducing the quantity!"); 
                book.setQuantity(quantity);
            }
            else
            {
                System.out.println("Books are not available right now!");
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
            boolean check = userId.equalsIgnoreCase(member.getMemberID());
            if(check)
            {      
                int CurrentAmountOfBorrowing = member.getCurrentAmountOfBorrowing(); // How many time one person can borrow a book left
                int AmountOfBorrowingForMember = member.getAmountOfBorrowingForMember(); // how many time did this person borrowed a book
                
                int AmountOfBorrowingForBooks = 1; // how many time a book being borrowed
               // ^^ lam chua lam
                
                if(CurrentAmountOfBorrowing > 0) 
                {
                    System.out.println("Borrowing Limit not exceeded!"); 
                    BorrowingTransaction newBT = new BorrowingTransaction();
                    
                    CurrentAmountOfBorrowing--;
                    newBT.setCurrentAmountOfBorrowing(CurrentAmountOfBorrowing);
                    
                    AmountOfBorrowingForMember++;
                    newBT.setAmountOfBorrowingForMembers(AmountOfBorrowingForMember);
                    
                    AmountOfBorrowingForBooks++;
                   //  newbook.setAmountOfBorrowingForBooks(AmountOfBorrowingForBooks);
                   
                   
                    //now adding new information to the transaction object    
                       newBT.setTransactionID( Utility.generateIDvTest(this, "Transaction") );
                       
                       ZonedDateTime DT = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")); // khai bao object date va time o ho chi minh, lay thoi gian ngay luc chay code nay
                       DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy ss:mm:HH"); // format custom
                       
                       String BorrowDateNTime = DT.format(format);
                       
                       if(member.getBorrowLimit() == 3) DT = DT.plusWeeks(1); // if regular DUE DATE = BORROWDATE + 1 tuan
                       else DT = DT.plusMonths(1); // else la premium DUE DATE = BORROWDATE + 1 thang
                       String DueDateNTime = DT.format(format);
                       
                       newBT.setBorrowDate(BorrowDateNTime); 
                       newBT.setDueDate(DueDateNTime); 
                       newBT.setFineAmount(0);
                       newBT.setReturnDate("Not Yet Settled");
                       
                       newBT.setStatus("Borrowed");
                       newBT.setMemberID(userId);
                       newBT.setBookID(bookId);
                       //Save object into ArrayList (Transaction List).
                       this.add(newBT);
                       
                       //Display success message.
                       System.out.println("Transaction have been Saved in the list!");
                       
                       //end
                       System.out.println("Press Enter to return!");
                       input.nextLine();
                       
                       return; 
                }
            }
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
        
        System.out.println("Size of TL is: " + this.size()); // -> optional
        if(this.isEmpty())
        {
            System.out.println("There are no Data in the transaction list database! (Press enter to return!)");
            input.nextLine();
            return;
        }

        
        ///////////////////////////////
        //User enters Transaction ID //
        ///////////////////////////////
        
        while(true)
        {
            System.out.print("Please Enter the Transaction ID: ");
            TransactionId = input.nextLine();
            if(TransactionId.length() != 0 && TransactionId.charAt(0) == 'T')
            {
                System.out.println("TransactionID ID Entered Successfully!");
                break;
            }
            else
            {
                System.out.println("Please Enter The Transaction ID correctly!");
                System.out.println("Press Enter to continue Or type Esc to return!");
                String e = input.nextLine();
                if(returnOrcontinue(e)) 
                {
                    return;
                }
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
                System.out.println("Transaction Found in the database!");
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
        
        System.out.println("Size of ML is: " + ML.size()); // -> optional
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
            System.out.print("Please Enter the user ID: ");
            userId = input.nextLine();
            if(userId.length() != 0 && userId.charAt(0) == 'M')
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
                System.out.println("User ID Found!");
                break;
            }
            else if(check == false && i == ML.size()-1)
            {
                System.out.println("User ID Not Found In The DataBase!"); 
                System.out.println("Press Enter to return!");
                String e = input.nextLine();
                return;
            }
        }    
        
        ////////////////////////////////////////////////////////////////////////////////////////
        // check if the book list is empty first before validaing the existence of the book ID//
        ////////////////////////////////////////////////////////////////////////////////////////
        
        System.out.println("Size of BookList is: " + BL.size()); // -> optional
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
            System.out.print("Please Enter the Book ID: ");
            bookId = input.nextLine();
            if(bookId.length() != 0 && bookId.charAt(0) == 'B')
            {
                System.out.println("book ID Entered Successfully!");
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
               System.out.println("The Book is exist and avaiable!");
               break;  // found book then break the loop no need to find anymore!
            }
            else if(checkB == false && i == BL.size()-1)
            {
                System.out.println("User ID Not Found In The DataBase!"); 
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
    
    //update info for currentBT
    ZonedDateTime DT = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
    DateTimeFormatter CustomFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy ss:mm:HH");
    
    //update ReturnDate info in BT
    String ReturnDateNTime = DT.format(CustomFormat);
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
    
    
    /////////////////////////////////////////////////
    //Turn String Time to Value Time
    int DueSeconds = Integer.parseInt(DueTimeS[0]);
    int DueMinutes = Integer.parseInt(DueTimeS[1]);
    int DueHours= Integer.parseInt(DueTimeS[2]);
    
    int ReturnSeconds = Integer.parseInt(ReturnTimeS[0]);
    int ReturnMinutes = Integer.parseInt(ReturnTimeS[0]);
    int ReturnHours = Integer.parseInt(ReturnTimeS[0]);
    
    
    /////////////////////////////////////////////////////
    //Compare DueDateNTIme with ReturnDateNTime
    int OverdueDays = 0;
    if(ReturnYears <= DueYears)
    {
        if(ReturnMonths <= DueMonths)
        {
            if(ReturnDays <= DueDays)
            {
                currentBT.setStatus("Returned");
                currentBT.setFineAmount(0);
                currentBT.setReturnDate(ReturnDateNTime);
                
                //
                int temp = currentBT.getCurrentAmountOfBorrowing();
                temp++;
                currentBT.setCurrentAmountOfBorrowing(temp);
                currentM.setCurrentAmountOfBorrowing(temp);     
            } 
            else OverdueDays = ReturnMaxDaysOfMonths(ReturnDays, DueMonths, ReturnMonths, ReturnYears);
        } 
        else OverdueDays = ReturnMaxDaysOfMonths(ReturnDays, DueMonths, ReturnMonths, ReturnYears);
    }
    else OverdueDays = ReturnMaxDaysOfMonths(ReturnDays, DueMonths, ReturnMonths, ReturnYears);
    
    if(OverdueDays > 0)
    {
         currentBT.setFineAmount(currentM.calculateFine(OverdueDays));
         if(OverdueDays > 3) currentBT.setStatus("OVERDUE [3]");
         else currentBT.setStatus("OVERDUE");
    }
    
    
    
    
    
    
    
    //OLD CODE
    /*    
        for(BorrowingTransaction memberBT: this)
        {
            boolean check = userId.equalsIgnoreCase(memberBT.getMemberID());
             //check whether member exists
            if(check)
            {
                System.out.println("User ID Found!");
                for(BorrowingTransaction BT : this)
                {
                    //Find borrowing transaction.
                    boolean BTcheck = userId.equalsIgnoreCase(BT.getMemberID());
                    if(BTcheck)
                    {
System.out.println("Transaction Found!");
                        //Enter return date.
                        while(true)
                        {
                            System.out.println("Enter the return date(dd/mm/yyyy): ");
                            returnDate = input.nextLine();
                            if(returnDate.length() == 9 && checkDate(returnDate))
                            {
                                System.out.println("Return Date Entered Successfully!");
                                break;
                            }
                         else if(returnDate.length() != 9 || !checkDate(returnDate))
                            {
                                System.out.println("Please Enter the correct format of return date!");
                                System.out.println("Press Enter to continue Or type Esc to return!");
                                String e = input.nextLine();
                                if(returnOrcontinue(e)) 
                                {
                                 return;
                                }
                            }
                        }
                   
                   
                        //Calculate overdue fine if necessary.                    
                        int OverdueFine = 0; // Default = 0, initialized so can change into specific formula later
                        memberBT.setFineAmount(OverdueFine);
                        for(Book book : BL)
                        {
                            check = book.getBookID().equalsIgnoreCase(memberBT.getBookID());
                            if(check) // check whether the boos is exist
                            {
                                //Increase book quantity.
                                int quan = book.getQuantity(); quan++;
                                book.setQuantity(quan);
                                //Update transaction status.
                                memberBT.setReturnDate(returnDate);
                                memberBT.setStatus("Returned");
                   
                                System.out.println("Transaction Updated!");
                                memberBT.toString(); // Display updated information of return
                                System.out.println("Press Enter to return!");
                                input.nextLine();
                                return; // end
                            }    
                        }
                    }
                    else
                    {
                        System.out.print("Transaction not found!");
                        System.out.println("Press Enter to continue Or type Esc to return!");
                        String e = input.nextLine();
                        if(returnOrcontinue(e)) 
                        {
                            return;
                        }
                    }
                }
}
            else
            {
                System.out.println("User ID Not Found!");
                System.out.println("Press Enter to return!");
                input.nextLine();
                return;
            }
        }
     */
    }
    
    
    
    public void displayBorrowedBooks()
    {
        if (this.isEmpty()) 
        {
            System.out.println("No transaction found!");
            return;
        }

    System.out.println("\n---------------- BORROWING TRANSACTION LIST ----------------");

    System.out.printf(
        "%-8s %-12s %-12s %-12s %-10s %-12s %-10s %-10s\n",
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
        for(BorrowingTransaction BT : this)
        {
            if(BT.getStatus().equalsIgnoreCase("OVERDUE") || BT.getStatus().equalsIgnoreCase("OVERDUE [3]")) System.out.println(BT.toString());
        }
    }
    
    public void viewMostPopularBooks()
    {
}
    
    public void viewMemberWithTheMostBorrowing()
    {
        int maxBorrowedValueOfPerson = -99999;
        int maxBorrowedPerson = 0;
        BorrowingTransaction BT = new BorrowingTransaction();
        for(int i = 0; i < this.size();i++)
        {
            BT = this.get(i);
            if(BT.getAmountOfBorrowingForMembers() > maxBorrowedValueOfPerson) 
            {
                maxBorrowedValueOfPerson = BT.getAmountOfBorrowingForMembers();
                maxBorrowedPerson = i;
            }
        }
        System.out.println("\n---------------- Member With The Most Borrowing Information----------------");
        System.out.println();
        
    }
}
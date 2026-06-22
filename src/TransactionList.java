import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime; // do mins, hours, day, month
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
    private LocalDateTime DateplusTime; // and object that can display both date and time
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH"); // cu phap
    
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
    
    //CheckDateFormat
    public boolean checkDate(String Date) // -> check if the input date from user is correct dd/mm/yyyy
    {
        if(Date.charAt(2) == '/' && Date.charAt(5) == '/') return true;
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
                int threshold = 3; // Standard limit for how many books one person can carry
                
                int CurrentAmountOfBorrowing = 1; // How many time one person can borrow a book left
                //^^^^^^^^^^^^^^^TEMPORARY => thang need to add this in Member Object! ==> int CurrentamountOfBorrowing = member.getCurrentamountofborrowing();
                
                int AmountOfBorrowingForMember = 1; // how many time did this person borrowed a book
                //^^^^^^^^^^^^^^^TEMPORARY => thang need to add this in Member Object! ==> int CurrentamountOfBorrowing = member.getCurrentamountofborrowing();
                
                int AmountOfBorrowingForBooks = 1; // how many time a book being borrowed
                //^^^^^^^^^^^^^^^TEMPORARY => lam need to add this in Book Object! ==> int AmountOfBorrowingForBooks = book.getAmountOfBorrowingForBooks();
                
                if(CurrentAmountOfBorrowing < threshold) 
                {
                    System.out.println("Borrowing Limit not exceeded!"); 
                    BorrowingTransaction newBT = new BorrowingTransaction();
                    
                    CurrentAmountOfBorrowing--;
                    newBT.setCurrentAmountOfBorrowing(CurrentAmountOfBorrowing);
                    
                    AmountOfBorrowingForMember--;
                    newBT.setAmountOfBorrowingForMembers(AmountOfBorrowingForMember);
                    
                    AmountOfBorrowingForBooks--;
                    newBT.setAmountOfBorrowingForBooks(AmountOfBorrowingForBooks);
                    //now adding new information to the transaction object    
                       newBT.setTransactionID( Utility.generateIDvTest(this, "Transaction") );
                       
                       
                       newBT.setBorrowDate("Not Yet Settled"); //need to know what type of member
                       newBT.setDueDate("Not Yet Settled"); //need to know what type of member
                       newBT.setFineAmount(0); //need to know what type of member
                       newBT.setReturnDate("Not Yet Settled"); //need to know what type of member
                       
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
        
        for(int i = 0; i < BL.size(); i++)
        {
            Book book = BL.get(i);
            boolean checkB = bookId.equalsIgnoreCase(book.getBookID()); // check bookId (input from user) and book.getBookID() (id from the book list!)
            //Check whether the book exists and is available.
            if(checkB == true) 
            {
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
        
    //UNFINISHED WORK!
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
   

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime; // do mins, hours, day, month
import java.time.format.DateTimeFormatter;


public class TransactionList extends ArrayList<BorrowingTransaction> 
{
    private MemberList ML;
    private BookList BL;
    private String userId = null;
    private String bookId = null;
    
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
    
    public boolean checkDate(String Date) // -> check if the input date from user is correct dd/mm/yyyy
    {
        if(Date.charAt(2) == '/' && Date.charAt(5) == '/') return true;
        else return false;
    }
    
    
    public void borrowBook()
    {
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        //User enters member ID and Book ID 
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
        //User enters Book ID 
        while(true)
        {
            System.out.print("Please Enter the Book ID: ");
            bookId = input.nextLine();
            if(bookId.length() != 0)
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
        // check if the member list is empty
        System.out.println("Size of ML is: " + ML.size());
        if(ML.size() == 0)
        {
            System.out.println("There are no Data in the member list database! (Press enter to return!)");
            input.nextLine();
            return;
        }
        
        for(Member member : ML)
        {
            boolean check = userId.equalsIgnoreCase(member.getMemberID());
            //check whether member exists
            if(check == true)
            {
                System.out.println("User ID Found!");
                
                for(Book book : BL)
                {
                 boolean checkB = bookId.equalsIgnoreCase(book.getBookID());
                 int quan = book.getQuantity();
                 //Check whether the book exists and is available.
                 if(checkB == true && quan > 0 ) 
                 {
                  System.out.println("The Book is exist and avaiable!");
                  
                  
                  
                  
                  int bLimit = member.getBorrowLimit(); //how many books member can borrow left
                  int threshold = 3; // How many books member can actually borrow
                  //Check member borrowing limit.
                  if(bLimit < threshold)
                  {
                       System.out.println("Borrowing Limit not exceeded!"); 
                       
                       //Create BorrowingTransaction object.
                       BorrowingTransaction newBT = new BorrowingTransaction();
                       
                       //Reduce book quantity.
                       System.out.println("Reducing the quantity"); 
                       quan--;
                       book.setQuantity(quan); 
                       //Update transaction infomation of the object
                       newBT.setTransactionID("TransacID:" + this.size()+1);
                       newBT.setBorrowDate("Not Yet Settled");
                       newBT.setDueDate("Not Yet Settled");
                       newBT.setFineAmount(-9999999);
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
                 else
                 {
                  System.out.println("The Book is unavailable!");
                  System.out.println("Press Enter to continue Or type Esc to return!");
                  String e = input.nextLine();
                  if(returnOrcontinue(e)) 
                    {
                        return;
                    }
                 }
                }
            }
                
        } 
        System.out.println("User ID Not Found In The DataBase!"); 
        System.out.println("Press Enter to return!");
        input.nextLine();
    }
    
    public void returnbook()
    {
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        String returnDate = null;
        //User enters member ID and Book ID
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
        //User enters Book ID 
        while(true)
        {
            System.out.print("Please Enter the Book ID: ");
            bookId = input.nextLine();
            if(bookId.length() != 0)
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
        // check if the member list is empty
        if(ML.isEmpty())
        {
            System.out.println("There are no Data in the member list database! (Press enter to return!)");
            input.nextLine();
            return;
        }
        
        
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
        }
        System.out.println("User ID Not Found!");
        System.out.println("Press Enter to return!");
        input.nextLine();
        return;
    }
    
    
    
    public void displayBorrowedBooks()
    {
        System.out.println("Displaying Borrowed Books: ");
        for (BorrowingTransaction memberBT : this)  System.out.println(memberBT.toString());
        
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
        
    }
}
   
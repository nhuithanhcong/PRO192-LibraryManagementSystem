import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime; // do mins, hours, day, month
import java.time.format.DateTimeFormatter;


public class TransactionList extends ArrayList<BorrowingTransaction> 
{
    
    private String memberID;
    private String bookID;
    private MemberList ML = new MemberList();
    private BookList BL = new BookList(); 
    
   

   
  
    
    
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
        

        String userId = null;
        String bookId = null;
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
        if(ML.size() == 0)
        {
            System.out.println("There are no Data in the member list database! (Press enter to return!)");
            input.nextLine();
            return;
        }
        
        for(int i = 0; i < ML.size(); i++)
        {
            boolean check = memberID.equalsIgnoreCase((ML.get(i)).getMemberID());
            //check whether member exists
            if(check == true)
            {
                System.out.println("User ID Found!");
                
                
                boolean checkB = bookID.equalsIgnoreCase((BL.get(i)).getBookID());
                int quan = (BL.get(i)).getQuantity();
                //Check whether the book exists and is available.
                if(checkB == true && quan > 0 ) 
                {
                  System.out.println("The Book is exist and avaiable!");
                  
                  
                  
                  
                  int bLimit = (ML.get(i)).getBorrowLimit();
                  int threshold = 3; // the amount of books one could borrow
                  //Check member borrowing limit.
                  if(bLimit < threshold)
                  {
                       System.out.println("Borrowing Limit not exceeded!"); 
                       
                       //Create BorrowingTransaction object.
                       BorrowingTransaction newBT = new BorrowingTransaction();
                       
                       //Reduce book quantity.
                       System.out.println("Reducing the quantity"); 
                       quan--;
                       BL.get(i).setQuantity(quan); 
                       
                       //Save transaction into ArrayList (Transaction List).
                       newBT.setTransactionID("TransacID:" + this.size()+1);
                       newBT.setBorrowDate("Not Yet Settled");
                       newBT.setDueDate("Not Yet Settled");
                       newBT.setFineAmount(-9999999);
                       newBT.setReturnDate("Not Yet Settled");
                       newBT.setStatus("Borrowed");
                       newBT.setMemberID(memberID);
                       newBT.setBookID(bookID);
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
        System.out.println("User ID Not Found In The DataBase!"); 
        System.out.println("Press Enter to return!");
        input.nextLine();
        return;
       
    }
    
    
    
    public void returnbook()
    {
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        
        String userId = null;
        String bookId = null;
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
        if(ML.isEmpty()) // ML.isEmpty() = [ML.length() != 0 -> 0|| == 0 -> 1]
        {
            System.out.println("There are no Data in the member list database! (Press enter to return!)");
            input.nextLine();
            return;
        }
        
        
        for(int i = 0; i < ML.size(); i++)
        {
            boolean check = memberID.equalsIgnoreCase((ML.get(i)).getMemberID());
             //check whether member exists
            if(check)
            {
                System.out.println("User ID Found!");
                
               //Find borrowing transaction.
               boolean BTcheck = memberID.equalsIgnoreCase((this.get(i)).getMemberID());
               if(BTcheck)
               {
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
                   BorrowingTransaction currentBT = this.get(i);
                   int OverdueFine = 0; // Default = 0, initialized so can change into specific formula later
                   currentBT.setFineAmount(OverdueFine);
                   
                   //Increase book quantity.
                   int quan = (BL.get(i)).getQuantity();
                   (BL.get(i)).setQuantity(quan++);
                   
                   //Update transaction status.
                   this.get(i).setReturnDate(returnDate);
                   System.out.println("Transaction Updated!");
                   this.get(i).toString();
                   System.out.println("Press Enter to return!");
                   input.nextLine();
                   return; // end
                   
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
        System.out.println("User ID Not Found!");
        System.out.println("Press Enter to return!");
        input.nextLine();
        return;
    }
    
    
    
    public void displayBorrowedBooks()
    {
        if (this.isEmpty()) {
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

    for (BorrowingTransaction transaction : this) {
        System.out.println(transaction);
    }

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
}
   

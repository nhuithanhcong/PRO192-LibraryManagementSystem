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
    
  
    
    
    
    public void borrowBook()
    {
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        

        
        //User enters member ID and Book ID 
        while(true)
        {
            System.out.print("Please Enter the user ID: ");
            String userId = input.nextLine();
            if(userId.length() != 0)
            {
                System.out.println("User ID Entered Successfully!");
                break;
            }
            else
            {
                System.out.println("Please Enter The User ID correctly! (Press Enter to continue)");
                input.nextLine();
                continue;
            }
        }
        //User enters Book ID 
        while(true)
        {
            System.out.print("Please Enter the Book ID: ");
            String bookId = input.nextLine();
            if(bookId.length() != 0)
            {
                System.out.println("book ID Entered Successfully!");
                break;
            }
            else
            {
                System.out.println("Please Enter The book ID correctly! (Press enter to continue!)");
                input.nextLine();
                continue;
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
                       
                       //Save transaction into ArrayList.
                       newBT.setTransactionID("");
                       newBT.setBorrowDate("");
                       newBT.setDueDate("");
                       newBT.setFineAmount(1);
                       newBT.setReturnDate("");
                       newBT.setStatus("Borrowed"); // 1 == borrowed
                       newBT.setMemberID(memberID);
                       newBT.setBookID(bookID);
                       this.add(newBT);
                       
                       //Display success message.
                       System.out.println("Transaction have been Saved in the list!");
                       
                       //end
                       input.close();
                       System.out.println("Press Enter to return!");
                       input.nextLine();
                       return; 
                  }
                }
                else
                {
                  System.out.println("The Book is unavailable!");
                  System.out.println("Press Enter to return!");
                  input.nextLine();
                  return;
                }
            }
                
        } 
        System.out.println("User ID Not Found In The DataBase!"); 
        input.nextLine();
        return;
       
    }
    
    
    
    public void returnbook()
    {
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        

        
        //User enters member ID and Book ID
        while(true)
        {
            System.out.print("Please Enter the user ID: ");
            String userId = input.nextLine();
            if(userId.length() != 0)
            {
                System.out.println("User ID Entered Successfully!");
                break;
            }
            else
            {
                System.out.println("Please Enter The User ID correctly! (Press Enter to continue)");
                input.nextLine();
                continue;
            }
        }
        //User enters Book ID 
        while(true)
        {
            System.out.print("Please Enter the Book ID: ");
            String bookId = input.nextLine();
            if(bookId.length() != 0)
            {
                System.out.println("book ID Entered Successfully!");
                break;
            }
            else
            {
                System.out.println("Please Enter The book ID correctly! (Press enter to continue!)");
                input.nextLine();
                continue;
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
            if(check)
            {
                System.out.println("User ID Found!");
                
               //Find borrowing transaction.
               boolean BTcheck = memberID.equalsIgnoreCase((this.get(i)).getMemberID());
               if(BTcheck)
               {
                   //Enter return date.
                   System.out.println("Enter the return date: ");
                   String returnDate = input.nextLine();
                   
                   //Calculate overdue fine if necessary.
                   BorrowingTransaction currentBT = this.get(i);
                   int OverdueFine = 0; // Default = 0, initialized so can change into specific formula later
                   currentBT.setFineAmount(OverdueFine);
                   
                   //Increase book quantity.
                   int quan = (BL.get(i)).getQuantity();
                   (BL.get(i)).setQuantity(quan++);
                   
                   //Update transaction status.
                   System.out.println("Transaction Updated!");
                   this.get(i).toString();
                   break; // end
                   
               }
               else
               {
                   System.out.print("Transaction not found!");
               }
            }
        }
        System.out.println("User ID Not Found!");
        System.out.println()
    }
    
    
    
    public void displayBorrowedBooks()
    {
        System.out.println("Displaying Borrowed Books: ");
        for(int i = 0; i < this.size();i++)
        {
            System.out.println(this.get(i).toString());
        }
        //Return to previous Menu
        Scanner input = new Scanner(System.in);
        
        try
        {
            System.out.println("Enter 0 to exit: ");
            int n = input.nextInt();
            return;
            
        }catch(Exception e)
        {
            System.out.println("Please enter 0!");
        }
    }
    public String displayBorrowingHistory()
    {
        System.out.println("Please Enter a member ID: ");
        Scanner input = new Scanner(System.in);
        String id = input.nextLine();
        //place input.nextLine() to test whther the "" is gone 
        for(int i = 0; i < this.size();i++)
        {
            boolean check = this.get(i).getMemberID().equalsIgnoreCase(id);
            if(check) 
            {
                return this.get(i).toString();
            }
        }
        

        input.next();
        return "Cannot find the user in database!";
        
    }
}
   

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.binding.NumberBinding;


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
        System.out.println("Please enter user ID: ");
        memberID = input.nextLine();
        System.out.println("Please enter book ID: ");
        bookID = input.nextLine();
        
       
        for(int i = 0; i < ML.size(); i++)
        {
            boolean check = memberID.equalsIgnoreCase((ML.get(i)).getMemberID());
             //check whether member exists
            if(check)
            {
                System.out.println("User ID Found!");
                
                //Check whether the book exists and is available.
                boolean checkB = bookID.equalsIgnoreCase((BL.get(i)).getBookID());
                int quan = (BL.get(i)).getQuantity();
                if(checkB == true && quan > 0 ) 
                {
                  System.out.println("The Book is exist and avaiable!");
                  
                  
                  //Check member borrowing limit.
                  int bLimit = (ML.get(i)).getBorrowLimit();
                  int threshold = 3; // the amount of books one could borrow
                  if(bLimit < 3)
                  {
                       System.out.println("Borrowing Limit not exceeded!"); 
                       
                       //Create BorrowingTransaction object.
                       BorrowingTransaction newBT = new BorrowingTransaction();
                       
                       //Reduce book quantity.
                       BL.get(i).setQuantity(quan--); 
                       
                       //Save transaction into ArrayList.
                       newBT.setTransactionID("");
                       newBT.setBorrowDate("");
                       newBT.setDueDate("");
                       newBT.setFineAmount(1);
                       newBT.setReturnDate("");
                       newBT.setStatus("1"); // 1 == borrowed
                       newBT.setMemberID(memberID);
                       newBT.setBookID(bookID);
                       this.add(newBT);
                       
                       //Display success message.
                       System.out.println("Transaction have been Saved in the list!");
                       
                       //end?
                       input.close();
                       break; 
                  }
                }
                else
                {
                  System.out.println("The Book is unavailable");
                    
                }
            }
            else if(check == false && i == ML.size()-1)
            {
                System.out.println("User ID Not Found"); 
                
            }
        } 
    }
    
    
    
    public void returnbook()
    {
        Scanner input = new Scanner(System.in); // tao object scanner de lay du lieu tu user
        

        
        //User enters member ID and Book ID
        System.out.println("Please enter user ID: ");
        memberID = input.nextLine();
        System.out.println("Please enter book ID: ");
        bookID = input.nextLine();
        
        
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
            else if(check == false && i == ML.size()-1)
            {
                System.out.println("USer ID Not Found"); 
                //Missing Exit The Method Statement
            }
        }
    }
}
   

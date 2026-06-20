import java.util.List;

public class Utility { 
    //HOW TO USE generateID
    /*
    String id/bookID = Utility.generateID(this, "member/book");
    System.out.println("Generated Member/book ID: " + id/bookID); 
    */
    public static String generateIDvTest(List<?> list, String type)
    {   
        // lay prefix cho ID
        String prefix = "";
        if (type.equalsIgnoreCase("book")) {
            prefix = "B";
        } else if (type.equalsIgnoreCase("member")) {
            prefix = "M";
        } else if (type.equalsIgnoreCase("Transaction")){
            prefix = "T";
        } else {return "Error: Invalid Type";}
        
        if (list == null || list.isEmpty()){return prefix + "0001";}
        
        // tim khoang
        String currentID = null;
        Object item = null;
        Book book;
        Member member;
        BorrowingTransaction BT;
        if (type.equalsIgnoreCase("book")) {
            for(int i = 0; i < list.size();i++)
            {
                book = (Book) list.get(i);
                currentID = book.getBookID();
                
                //get the value from the number id (LIST)
                String idNumStrList = currentID.substring(1); // Substring(position) M0002 (M co vi tri la 0) chung ta chi lay VALUE nen position se bang 1
                int idNumList = Integer.parseInt(idNumStrList);
                
                //kiem tra xem gia tri ID tu vong lap va gia tri ID tu list co bi lech gia tri hay khong
                // neu co thi chung ta se su dung ID tu vong lap
                // checking id from 0 -> list.size()
                    String idNumStrLoop = String.format("%04d", i + 1); //0001 
                    int idNumLoop = Integer.parseInt(idNumStrLoop);
                    if(idNumLoop != idNumList)  book.setBookID(prefix + idNumStrLoop);
            }
            return prefix + String.format("%04d", list.size() + 1);
        } else if (type.equalsIgnoreCase("member")) {
            for(int i = 0; i < list.size();i++)
            { 
                //getting the data from list
                member = (Member) list.get(i); // (member) goi la casting noi cach khac la EP/FORCE cai list.get(i) bien thanh Object theo kieu CLASS MEMBER
                currentID = member.getMemberID();
                
                //get the value from the number id (LIST)
                String idNumStrList = currentID.substring(1); // Substring(position) M0002 (M co vi tri la 0) chung ta chi lay VALUE nen position se bang 1
                int idNumList = Integer.parseInt(idNumStrList);
                
                //kiem tra xem gia tri ID tu vong lap va gia tri ID tu list co bi lech gia tri hay khong
                // neu co thi pushback (gia tri ID tu n thanh n - 1)
                // checking id from 0 -> list.size()
                    String idNumStrLoop = String.format("%04d", i + 1); //0001 
                    int idNumLoop = Integer.parseInt(idNumStrLoop);
                    if(idNumLoop != idNumList)  member.setMemberID(prefix + idNumStrLoop);
            }
        return prefix + String.format("%04d", list.size() + 1); 
        } else if (type.equalsIgnoreCase("Transaction")){
            for(int i = 0; i < list.size();i++)
            {
                BT = (BorrowingTransaction) list.get(i);
                currentID = BT.getTransactionID();
                //get the value from the number id (LIST)
                String idNumStrList = currentID.substring(1); // Substring(position) M0002 (M co vi tri la 0) chung ta chi lay VALUE nen position se bang 1
                int idNumList = Integer.parseInt(idNumStrList);
                
                //kiem tra xem gia tri ID tu vong lap va gia tri ID tu list co bi lech gia tri hay khong
                // neu co thi chung ta se su dung ID tu vong lap
                // checking id from 0 -> list.size()
                    String idNumStrLoop = String.format("%04d", i + 1); //0001 
                    int idNumLoop = Integer.parseInt(idNumStrLoop);
                    if(idNumLoop != idNumList)  BT.setTransactionID(prefix + idNumStrLoop);
            }
            return prefix + String.format("%04d", list.size() + 1);
        }
        return "Error: you suck bro"; // sao cung duoc do ham yeu cau
    }
   
    public static void clearScreen() {
    // In ra 50 dong cho troi het di :)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}


    
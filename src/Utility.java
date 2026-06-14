import java.util.List;

public class Utility {
    public static String generateBookID(List<Book> bookList) {
        if (bookList == null || bookList.isEmpty()){
            return "B0001";
        }
        
        int maxIdNum = 0;
        
        for (Book book : bookList) {
            if (book != null && book.getBookID() != null && book.getBookID().startsWith("B")) {
                try{
                    String idNumStr = book.getBookID().substring(1);
                    int idNum = Integer.parseInt(idNumStr);
                    if (idNum > maxIdNum) {
                    maxIdNum = idNum;
                    }
                } catch (NumberFormatException e){}
            }
        } 
        return "B" + String.format("%04d", maxIdNum + 1);
    }
    
    public static String generateMemberID(List<Member> memberList) {
        if (memberList == null || memberList.isEmpty()){
            return "M0001";
        }
        
        int maxIdNum = 0;
        
        for (Member member : memberList) {
            if (member != null && member.getMemberID()!= null && member.getMemberID().startsWith("M")) {
                try{
                    String idNumStr = member.getMemberID().substring(1);
                    int idNum = Integer.parseInt(idNumStr);
                    if (idNum > maxIdNum) {
                    maxIdNum = idNum;
                    }
                } catch (NumberFormatException e){}
            }
        } 
        return "M" + String.format("%04d", maxIdNum + 1);
    }
}

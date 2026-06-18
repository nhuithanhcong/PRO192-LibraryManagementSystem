import java.util.List;

public class Utility { 
    //HOW TO USE generateID
    /*
    String id/bookID = Utility.generateID(this, "member/book");
    System.out.println("Generated Member/book ID: " + id/bookID); 
    */
    public static String generateID(List<?> list, String type) {
        //xac dinh tien to dua tren chuoi nguoi dung nhap
        String prefix = "";
        if (type.equalsIgnoreCase("book")) {
            prefix = "B";
        } else if (type.equalsIgnoreCase("member")) {
            prefix = "M";
        } else {return "Error: Invalid Type";}
        
        // neu danh sach rong 
        if (list == null || list.isEmpty()){return prefix + "0001";}
        
        int maxIdNum = 0;
        
        for (Object item : list) {// duyet danh sach
            String currentId = null;

            if (type.equalsIgnoreCase("book") && item instanceof Book) {
                Book book = (Book) item;
                currentId = book.getBookID();
            } else if (type.equalsIgnoreCase("member") && item instanceof Member) {
                Member member = (Member) item;
                currentId = member.getMemberID();
            }

            // Find the highest ID value
            if (currentId != null && currentId.startsWith(prefix)) {
                try {
                    String idNumStr = currentId.substring(1);
                    int idNum = Integer.parseInt(idNumStr);
                    if (idNum > maxIdNum) {
                        maxIdNum = idNum;
                    }
                } catch (NumberFormatException e) {}
            }
        } 
        return prefix + String.format("%04d", maxIdNum + 1);
    }
}


    
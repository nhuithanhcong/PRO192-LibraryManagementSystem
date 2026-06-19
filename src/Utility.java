import java.util.List;

public class Utility { 
    //Huong dan su dung: generateID()
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
    
    public static void clearScreen() {
    // In ra 50 dong cho troi het di :)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    //Huong dan su dung: delay()
    /*
    Utility.delay(so milisec delay);
    Neu co loi Delay Function interruted!!! goi nguoi viet ham nay
    */
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Delay Function interruted!!!-Request fixes"); // canh bao de khac phuc van de
            Thread.currentThread().interrupt(); // Khoi phuc trang thai ngu cua thread neu co loi
        }
    }
    
    public static boolean isValidPhoneNumber(String phone) {
        //kiem tra null va do dai phai 10
        if (phone == null || phone.length() != 10) {return false;}
    
        // ky tu dau tien phai la '0'
        if (phone.charAt(0) != '0') {return false;}
    
        // Kiem tra 9 ky tu con lai co phai la so khong
        for (int i = 1; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {return false;}
        }
        return true;
    }
    
    public static boolean isValidEmail(String email) {
        // Kiem tra null hoac rong
        if (email == null || email.trim().isEmpty()) {return false;}

        email = email.trim();

        // Tim vi tri cua ky tu '@'
        int atIndex = email.indexOf('@');
        // '@' khong o đau, khong o cuoi, chi xuat hien 1 lan
        if (atIndex <= 0 || atIndex == email.length() - 1 || atIndex != email.lastIndexOf('@')) {return false;}

        //Tach phan ten (local part) và phan ten mien (domain part)
        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1);

        //Kiem tra dau cham '.' trong phan domain
        int dotIndex = domainPart.lastIndexOf('.');
        // Dau cham khong o đau, khong o cuoi cua domain
        if (dotIndex <= 0 || dotIndex == domainPart.length() - 1) {return false;}

        // Kiem tra ky tu khoang trang thua trong email
        if (localPart.contains(" ") || domainPart.contains(" ")) {return false;}
        return true;
    }
    
    //Ham kiem tra chung cho Title, Genre, Name, Author (Chi can khong đe trong)
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }
}   
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
        return "Error!"; // sao cung duoc do ham yeu cau
    }
   
    public static void clearScreen() {
    // In ra 50 dong cho troi het di :)
        for (int i = 0; i < 50; i++) {
            System.out.println();
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
        // Kiem tra null, rong hoac chua khoang trang
    if (email == null || email.trim().isEmpty() || email.contains(" ")) {
        return false;
    }

    email = email.trim();
    int atIndex = email.indexOf('@');

    // 2. '@' phai ton tai, khong o đau/cuoi va la duy nhat
    if (atIndex <= 0 || atIndex == email.length() - 1 || atIndex != email.lastIndexOf('@')) {
        return false;
    }

    // 3. Kiem tra dau '.' trong phan domain (tinh tu sau ky tu '@')
    int dotIndex = email.lastIndexOf('.');
    
    // Dau '.' phai nam SAU dau '@' it nhat 1 ky tu va KHONG đuoc o cuoi cung
    if (dotIndex <= atIndex + 1 || dotIndex == email.length() - 1) {
        return false;
    }
    
    return !email.contains("..");
    }
    
    //Ham kiem tra chung cho Title, Genre, Name, Author (Chi can khong đe trong)
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }
    
    public static boolean isValidPublicationYear(int year) {
        int currentYear = java.time.Year.now().getValue();
        return year > 0 && year <= currentYear;
}
    
    public static boolean isValidDate(String dateStr) {
    if (dateStr == null || dateStr.trim().isEmpty()) return false;

    java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try {
        java.time.LocalDate.parse(dateStr.trim(), dtf);
        return true;
    } catch (java.time.format.DateTimeParseException e) {
        return false;
    }
}
    
    public static int tryCatchInt(java.util.Scanner scanner, String print){
        while(true){
            System.out.print(print);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Xoa bo nho dem
                return value;
            }catch (java.util.InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.nextLine(); // Xoa ky tu loi khoi bo nho dem
            }
        }
    }
} 

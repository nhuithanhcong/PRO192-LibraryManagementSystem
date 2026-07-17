import java.util.List;

public class Utility { 
    //Huong dan su dung: generateID()
    /*
    String id/bookID = Utility.generateID(this, "member/book");
    System.out.println("Generated Member/book ID: " + id/bookID); 
    */
    public static String generateIDvTest(List<?> list, String type) {
        String prefix = "";
        if      (type.equalsIgnoreCase("book"))         prefix = "B";
        else if (type.equalsIgnoreCase("member"))       prefix = "M";
        else if (type.equalsIgnoreCase("Transaction"))  prefix = "T";
        else return "Error: Invalid Type";
        
        if (list == null || list.isEmpty()){return prefix + "0001";}
        
        // lay phan tu cuoi cung
        String currentID = "";
        Object   lastItem = list.get(list.size() - 1);
        
        //instanceof Dung de hoi lastItem co phai duoc tao ra tu BOOK/MEMBER/BT KHONG
        //kiem tra TRUE thi moi ep kieu
        if      (lastItem instanceof Book)                 currentID = ((Book)                 lastItem).getBookID();
        else if (lastItem instanceof Member)               currentID = ((Member)               lastItem).getMemberID();
        else if (lastItem instanceof BorrowingTransaction) currentID = ((BorrowingTransaction) lastItem).getTransactionID();
        
        int idNumList = Integer.parseInt(currentID.substring(1));
        return prefix + String.format("%04d", idNumList + 1);
    }
   
    public static void clearScreen() {
    // In ra 50 dong cho troi het di 
        for (int i = 0; i < 50; i++) System.out.println();
    }
      
    public static boolean isValidPhoneNumber(String phone) {
        //kiem tra null va do dai phai 10
        if (phone == null || phone.length() != 10)      return false;
        
        // ky tu dau tien phai la '0'
        if (phone.charAt(0) != '0')                     return false;
        
        // Kiem tra 9 ky tu con lai co phai la so khong
        for (int i = 1; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))    return false;
        }
        return true;
    }
    
    public static boolean isValidEmail(String email) {
        // Kiem tra null, rong hoac chua khoang trang
        if (email == null || email.trim().isEmpty() || email.contains(" "))                     return false;

        email = email.trim();
        int atIndex = email.indexOf('@');

        //'@' phai ton tai, khong o đau/cuoi va la duy nhat
        if (atIndex <= 0 || atIndex == email.length() - 1 || atIndex != email.lastIndexOf('@')) return false;

        //Kiem tra dau '.' trong phan domain (tinh tu sau ky tu '@')
        int dotIndex = email.lastIndexOf('.');
    
        //Dau '.' phai nam SAU dau '@' it nhat 1 ky tu va KHONG đuoc o cuoi cung
        if (dotIndex <= atIndex + 1 || dotIndex == email.length() - 1 || dotIndex != email.lastIndexOf('.'))                          return false;
        return true;
    
    }
    
    //Ham kiem tra chung cho Title, Genre, Name, Author (Chi can khong đe trong)
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }
    
    public static boolean isValidPublicationYear(int year) {
        int currentYear = java.time.Year.now().getValue();
        return year > 0 && year <= currentYear;
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

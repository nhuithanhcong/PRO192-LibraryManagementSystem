import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class BookList extends ArrayList<Book> implements GeneralUtil {

    // Them sach moi vao danh sach
    @Override
    public void add() {
        Scanner input = new Scanner(System.in);
        System.out.println("---ADD A NEW BOOK---");
        
        /*System.out.print("Enter book ID: ");
        String bookID = input.nextLine();
        
        // Kiem tra ID da ton tai chua 
        boolean isExisted = false;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equalsIgnoreCase(bookID)) {
                isExisted = true;
                break;
            }
        }
        
        if (isExisted == true) {
            System.out.println("Book ID already exists!");
            return; 
        }*/
        
        String bookID = Utility.generateIDvTest(this, "book");
        System.out.println("Generated Member ID: " + bookID);
        
        System.out.print("Enter book title: ");
        String title = input.nextLine();
        System.out.print("Enter book author: ");
        String author = input.nextLine();
        System.out.print("Enter book genre: ");
        String genre = input.nextLine();
        
        // kiem tra publication year
        int publicationYear = 0;
        while (true) {
            System.out.print("Enter book publication year: ");
            
            // Neu dung la so nguyen
            if (input.hasNextInt() == true) {
                publicationYear = input.nextInt();
                input.nextLine(); 
                
                if (publicationYear <= 0) {
                    System.out.println("Year must be a positive number! Try again.");
                } else {
                    break; 
                }
            } 
            // Neu nguoi dung nhap chu 
            else {
                System.out.println("Invalid format! Please enter a valid number.");
                input.nextLine(); 
            }
        }
        
        // kiem tra quantity
        int quantity = 0;
        while (true) {
            System.out.print("Enter book quantity: ");
            // Neu dung la so nguyen
            if (input.hasNextInt() == true) {
                quantity = input.nextInt();
                input.nextLine(); 
                
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative! Try again.");
                } else {
                    break;
                }
            }
            // Neu nguoi dung nhap chu 
            else {
                System.out.println("Invalid format! Please enter a valid number.");
                input.nextLine(); 
            }
        }
        
        Book info = new Book(bookID, title, author, genre, publicationYear, quantity, 0, 0, 0);
        info.setAvailableCopies(quantity); 
        this.add(info);
        System.out.println("Book added successfully!");
    } 
        
    // Cap nhat thong tin sach
    @Override
    public void update() {
        Scanner input = new Scanner(System.in);
        System.out.println("---UPDATE A BOOK INFO---");
        System.out.print("Enter book ID: ");
        String bookID = input.nextLine();
        
        Book foundBook = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equalsIgnoreCase(bookID)) {
                foundBook = this.get(i);
                System.out.println("Current infor: " + foundBook.toString());
                break;
            }
        }
        
        if (foundBook != null) {
            System.out.print("Update book title: ");
            foundBook.setTitle(input.nextLine());
        
            System.out.print("Update book author: ");
            foundBook.setAuthor(input.nextLine());
       
            System.out.print("Update book genre: ");
            foundBook.setGenre(input.nextLine());
       
            // Kiem tra publication year khi update
            while (true) {
                System.out.print("Update book publication year: ");
                if (input.hasNextInt() == true) {
                    int publicationYear = input.nextInt();
                    input.nextLine();
                    
                    if (publicationYear <= 0) {
                        System.out.println("Year must be a positive number! Try again.");
                    } else {
                        foundBook.setPublicationYear(publicationYear);
                        break;
                    }
                } else {
                    System.out.println("Invalid format! Please enter a valid number.");
                    input.nextLine();
                }
            }
        
            // Kiem tra quantity khi update
            while (true) {
                System.out.print("Update book quantity: ");
                if (input.hasNextInt() == true) {
                    int quantity = input.nextInt();
                    input.nextLine();
                    
                    if (quantity < 0) {
                        System.out.println("Quantity cannot be negative! Try again.");
                    } else {
                        foundBook.setQuantity(quantity);
                        foundBook.setAvailableCopies(quantity); 
                        break;
                    }
                } else {
                    System.out.println("Invalid format! Please enter a valid number.");
                    input.nextLine();
                }
            }
            
            System.out.println("Book info successfully updated!");
        } else {
            System.out.println("Book not found!");
        }
    }
        
    // Xoa sach khoi danh sach
    @Override
    public void delete() {
        Scanner input = new Scanner(System.in);
        System.out.println("---DELETE A BOOK---");
        System.out.print("Enter book ID: ");
        String bookID = input.nextLine();
        
        Book removeBook = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBookID().equalsIgnoreCase(bookID)) {
                removeBook = this.get(i);
                break;
            }
        }
        
        if (removeBook != null) {
            System.out.println("Book Found.");
            System.out.println("Book infor: " + removeBook.toString());
            this.remove(removeBook);
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }
    
    // Hien thi danh sach sach
    @Override
    public void display() {

    if (this.isEmpty()) {
        System.out.println("No available books!");
        return;
    }

    System.out.println("\n------------ BOOK LIST ------------");

    System.out.printf(
        "%-6s %-25s %-25s %-15s %-6s %-4s\n",
        "ID",
        "Title",
        "Author",
        "Genre",
        "Year",
        "Qty"
    );

    System.out.println("------------------------------------------------------------------------------------");

    for (Book book : this) {
        System.out.println(book.toString());
    }

    System.out.println("------------------------------------------------------------------------------------");
}
    // Tim kiem sach
    @Override
    public void search() {
        Scanner input = new Scanner(System.in);
        System.out.println("---SEARCH BOOK---");
        System.out.print("Enter keyword (title, author, or genre) to search: ");
        String keyword = input.nextLine().toLowerCase();
        
        if (keyword.length() == 0) {
            System.out.println("Keyword cannot be empty!");
            return;
        }
        
        boolean isFound = false;
        System.out.println("\n---SEARCH RESULTS---");
        
        for (int i = 0; i < this.size(); i++) {
            Book book = this.get(i);
            String title = book.getTitle().toLowerCase();
            String author = book.getAuthor().toLowerCase();
            String genre = book.getGenre().toLowerCase();
            
            if (title.contains(keyword) || author.contains(keyword) || genre.contains(keyword)) {
                isFound = true;
                System.out.println(book.toString());
            }
        }
        
        if (isFound == false) {
           System.out.println("No books match your search keyword.");
        } 
    }
}

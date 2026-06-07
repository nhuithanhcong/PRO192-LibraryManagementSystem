import java.util.ArrayList;
import java.util.Scanner;

public class BookList extends ArrayList<Book> implements GeneralUtil{
    //Them sach moi vao danh sach
    @Override
    public void add(){
        //tao scanner de nhap info cua sach
        Scanner input = new Scanner(System.in);
        System.out.println("---ADD A NEW BOOK---");
        
        System.out.println("Enter book ID: ");
        String bookID = input.nextLine();
        System.out.println("Enter book title: ");
        String title = input.nextLine();
        System.out.println("Enter book author: ");
        String author = input.nextLine();
        System.out.println("Enter book genre: ");
        String genre = input.nextLine();
        System.out.println("Enter book puplication year: ");
        int publicationYear = input.nextInt();
        System.out.println("Enter book quantity: ");
        int Quantity = input.nextInt();
        Book info = new Book(bookID, title, author, genre, publicationYear, Quantity);
        
        //kiem tra xem sach da co hay chua
        int size = this.size();
        for(int i=0;i<size;i++){
            Book book = this.get(i);
            book.getBookID();
            if (book.getBookID().equalsIgnoreCase(bookID)){
                //id sach da co trong list
                System.out.println("Book ID is already existed!");
            }
            else {
                //id chua co trong list, them sach moi
                this.add(info);
                System.out.println("Book added successfully!");
            }
        }
    }
    
    //cap nhat info cua sach
    @Override
    public void update(){
        //nhap id de tim sach 
        Scanner input = new Scanner(System.in);
        System.out.println("---UPDATE A BOOK INFO---");
        System.out.println("Enter book ID: ");
        String bookID = input.nextLine();
        
        //kiem tra id cua sach
        int size = this.size();
        for(int i=0;i<size;i++){
            Book book = this.get(i);
            book.getBookID();
            if(book.getBookID().equalsIgnoreCase(bookID)){
                //id co trong list, thay doi info sach
                System.out.println("Update book title: ");
                String title = input.nextLine();
                book.setTitle(title);
            
                System.out.println("Update book author: ");
                String author = input.nextLine();
                book.setAuthor(author);
           
                System.out.println("Update book genre: ");
                String genre = input.nextLine();
                book.setGenre(genre);
           
                System.out.println("Update book publication year: ");
                int publicationYear = input.nextInt();
                book.setPublicationYear(publicationYear);
            
                System.out.println("Update book quantity: ");
                int quantity = input.nextInt();
                book.setQuantity(quantity);
                System.out.println("Book is info successfully updated!");
            }
            else{
                //id khong co trong list
                System.out.println("Book is not found!");
            }
        }
    }
        
    //xoa sach khoi list
    @Override
    public void delete(){
        //nhap id sach
        Scanner input = new Scanner(System.in);
        System.out.println("---DELETE A BOOK---");
        System.out.println("Enter book ID: ");
        String bookID = input.nextLine();
        
        //tao object de gan sach muon tim vao removeBook
        Book removeBook=null;
        
        //kiem tra id sach
        int size = this.size();
        for(int i=0;i<size;i++){
            Book book = this.get(i);
            book.getBookID();
            book.getQuantity();
            if(book.getBookID().equalsIgnoreCase(bookID)){
                //id ton tai trong list
                removeBook=book;
                System.out.println("Book Found: ");
                System.out.println("book.toString()");
                break;
            }
        }
        if (removeBook!=null){
            //neu ton tai id sach thi xoa sach di
            this.remove(removeBook);
        }
        else{
            //id sach khong ton tai
            System.out.println("Book is not found!");
        }
    }
    
    //hien info sach
    @Override
    public void display(){
        int size = this.size();
        if (size == 0) {
            System.out.println("No available book!");
            return;
        }
        for (int i = 0; i < size; i++) {
            Book book = this.get(i);
            System.out.println("---DISPLAY BOOK INFO---");
            System.out.println(book.toString());
        }
    }
    
    //tim sach theo id hoac ten sach
    @Override
    public void search() {
        //chon giua 2 cach tim kiem
        Scanner input = new Scanner(System.in);
        System.out.println("---SEARCH BOOK---");
        System.out.println("[1]Search by ID      [2]Search by name");
        System.out.println("Choose");
        int choice = input.nextInt();
        if(choice==1){
            System.out.println("Enter book ID: ");
            String bookID = input.nextLine();
        
            boolean isFound = false;
            int size = this.size();
            for (int i = 0; i < size; i++) {
                Book book = this.get(i);
                if (book.getBookID().equalsIgnoreCase(bookID)) {
                    isFound = true;
                    System.out.println("Book found: ");
                    System.out.println(book.toString());
                    break;       
                }
            }
            if (!isFound) {
               System.out.println("Book not found.");
            } 
        }
        else{
            System.out.println("Enter book title: ");
            String title = input.nextLine();
        
            boolean isFound = false;
            int size = this.size();
for (int i = 0; i < size; i++) {
                Book book = this.get(i);
                if (book.getTitle().equalsIgnoreCase(title)) {
                    isFound = true;
                    System.out.println("Book found: ");
                    System.out.println(book.toString());
                    break;       
                }
            }
            if (!isFound) {
               System.out.println("Book not found.");
            } 
        }
    }
}
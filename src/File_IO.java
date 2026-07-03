import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class File_IO 
{
    private TransactionList TL;
    private MemberList ML;
    private BookList BL;
    //WRITING FILE

    public void setTL(TransactionList TL) {
        this.TL = TL;
    }

    public void setML(MemberList ML) {
        this.ML = ML;
    }

    public void setBL(BookList BL) {
        this.BL = BL;
    }
    
    public void test()
    {
        System.out.println( "TL size is:" + TL.size() );
    }
    public void createFileforBook() throws IOException
    {
        FileWriter obj = new FileWriter("BookList.txt");
        for (Book b : BL) {
            obj.write(b.getBookID() + ", " + b.getTitle() + ", " + b.getAuthor() + ", " + b.getGenre() + ", " + b.getPublicationYear() + ", "
                    + b.getQuantity() + "\n");
        }
        obj.close();
    }
    public void createFileforUser() throws IOException
    {
        FileWriter obj = new FileWriter("MemberList.txt");  
        for (Member m : ML) {
            obj.write(m.getMemberID() + ", " + m.getName() + "," + m.getPhone() + ", " + m.getEmail() + ", " + m.getStatus() + "\n");
        }
        obj.close();
    }
    public void createFileforBorrowingBook() throws IOException
    {
            FileWriter obj= new FileWriter("TransactionList.txt");  
    }
    public void createFileforReturningBook() throws IOException
    {
            FileWriter obj= new FileWriter("ReturningBook.txt");  
    }

    
    //READING FILE
    /*Trong loadFIle hay readFile ta can phai Book book va add cai book do vao bookList moi vi du lieu khi ta write se tam luu vao ram =>> khi ta
    thoat chuong trinh du lieu trong writefile se bien mat va de lai 1 file txt, do do sau khi loadFile doc duoc file txt do ta can tao 1 data book moi
     roi them book do vao booklist thi luc do size cua bookList se k con la 0 nua ma bang voi du lieu hien tai trong file txt*/
    public void loadFileForBook() throws FileNotFoundException {
        File obj = new File("BookList.txt");
        try (Scanner myReader = new Scanner(obj)) {
            while (myReader.hasNextLine()) { //neu myreader co ton tai data
                String data = myReader.nextLine();//thi doc noi dung data hien tai va xuong dong de doc noi dung tiep theo
                String[] info = data.split(",");//roi sau do tach data do ra de Book book moi
                String bookID = info[0].trim();
                String title = info[1].trim();
                String author = info[2].trim();
                String genre = info[3].trim();
                int year = Integer.parseInt(info[4].trim());// chuyen String "year" thanh int year 
                int quantity = Integer.parseInt(info[5].trim());// chuyen String "quant" thanh int quant
                Book book = new Book(bookID, title, author, genre, year, quantity, 0, 0, 0);
                BL.add(book);
            }
        }
    }
    public void loadFileForMember() throws FileNotFoundException {
        File obj = new File("MemberList.txt");
        try (Scanner myReader = new Scanner(obj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] info = data.split(",");
                String memberID = info[0].trim();
                String name = info[1].trim();
                String phone = info[2].trim();
                String email = info[3].trim();
                String status = info[4].trim();
                Member member = new Member(memberID, name, phone, email, status, 0, 0);
                ML.add(member);
             }
        }
    }
    
}
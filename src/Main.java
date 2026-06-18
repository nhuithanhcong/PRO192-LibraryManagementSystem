import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println(" _   _  _   _   ____   __  __     _  _   ");
        System.out.println("| \\ | || | | | / __ \\ |  \\/  |   | || |  ");
        System.out.println("|  \\| || |_| || |  | || \\  /|   | || |_ ");
        System.out.println("| . ` ||  _  || |  | || |\\/| |   |__   _|");
        System.out.println("| |\\  || | | || |__| || |  | |      | |  ");
        System.out.println("|_| \\_||_| |_| \\____/ |_|  |_|      |_|  ");
        System.out.println("                                   ");
        
        LibraryManager LM = new LibraryManager();

        
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do{
            //show main menu
            System.out.println("===================================");
            System.out.println("LIBRARY MANAGEMENT SYSTEM");
            System.out.println("===================================");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Borrowing/Returning");
            System.out.println("4. Reports");
            System.out.println("5. Clear screen");
            System.out.println("6. Exit");
            System.out.println("-----------------------------------");
            System.out.print("Choose an option: ");
            
            try{
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    int bookChoice;
                    do {
                        System.out.println("---------Manage Books---------");
                        System.out.println("1. Add new book.");
                        System.out.println("2. Update book information.");
                        System.out.println("3. Remove a book.");
                        System.out.println("4. View all books.");
                        System.out.println("5. Search books by title, author, or genre.");
                        System.out.println("6. Clear screen");
                        System.out.println("7. Back to Main Menu");
                        System.out.print("Choose a sub-option: ");
                        try {
                            bookChoice = scanner.nextInt();
                            switch (bookChoice) {
                                case 1:
                                    LM.addBook();
                                    break;
                                case 2:
                                    LM.updateBook();
                                    break;
                                case 3:
                                    LM.removeBook();
                                    break;
                                case 4:
                                    LM.viewAllBook();
                                    break;
                                case 5:
                                    LM.searchBookByTerms();
                                    break;
                                case 6:    
                                    Utility.clearScreen();
                                    break;
                                case 7:
                                    System.out.println("Returning to Main Menu...");
                                default:
                                    System.out.println("\nInvalid sub-option! Choose between 1 and 7.");
                            }
                        }catch (InputMismatchException e) {
                            System.out.println("\nError: Invalid input. Please enter a number.");
                            scanner.next();
                            bookChoice = 0;
                        }
                    } while (bookChoice != 7);
                    break;
                    
                case 2:
                    int MemberChoice;
                    do {
                        System.out.println("---------Manage Members---------");
                        System.out.println("1. Add new member.");
                        System.out.println("2. Update member information.");
                        System.out.println("3. Remove a member.");
                        System.out.println("4. View all members.");
                        System.out.println("5. Search members by name or ID.");
                        System.out.println("6. Clear screen");
                        System.out.println("7. Back to Main Menu");
                        System.out.print("Choose a sub-option: ");
                        try {
                            MemberChoice = scanner.nextInt();
                            switch (MemberChoice) {
                                case 1:
                                    LM.addNewMember();
                                    break;
                                case 2:
                                    LM.updateMemberInfo();
                                    break;
                                case 3:
                                    LM.removeMember();
                                    break;
                                case 4:
                                    LM.viewAllMember();
                                    break;
                                case 5:
                                    LM.searchMemberByIdOrName();
                                    break;
                                case 6:    
                                Utility.clearScreen();
                                break;
                                case 7:
                                    System.out.println("Returning to Main Menu...");
                                default:
                                    System.out.println("\nInvalid sub-option! Choose between 1 and 7.");
                            }
                        }catch (InputMismatchException e) {
                            System.out.println("\nError: Invalid input. Please enter a number.");
                            scanner.next();
                            MemberChoice = 0;
                        }
                    } while (MemberChoice != 7);
                    break;
                    
                case 3:
                    int BRChoice;
                    do {
                        System.out.println("---------Borrowing/Returning---------");
                        System.out.println("1. Borrow a book.");
                        System.out.println("2. Return a book.");
                        System.out.println("3. View all borrowed books.");
                        System.out.println("4. View borrowing history for a specific member.");
                        System.out.println("5. Clear screen");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Choose a sub-option: ");
                        try {
                            BRChoice = scanner.nextInt();
                            switch (BRChoice) {
                                case 1:
                                    LM.borrowBook();
                                    break;
                                case 2:
                                    LM.returnBook();
                                    break;
                                case 3:
                                    LM.viewBorrowedBooks();
                                    break;
                                case 4:
                                    LM.viewBorrowingHistoryOfSpecificMember();
                                    break;
                                case 5:    
                                    Utility.clearScreen();
                                    break;
                                case 6:
                                    System.out.println("Returning to Main Menu...");
                                default:
                                    System.out.println("\nInvalid sub-option! Choose between 1 and 6.");
                            }
                        }catch (InputMismatchException e) {
                            System.out.println("\nError: Invalid input. Please enter a number.");
                            scanner.next();
                            BRChoice = 0;
                        }
                    } while (BRChoice != 6);
                    break;
                    
                case 4:
                    
                    int reportChoice;
                    do {
                        System.out.println("---------Reports---------");
                        System.out.println("1. List of all currently borrowed books.");
                        System.out.println("2. List of overdue books.");
                        System.out.println("3. List most popular books.");
                        System.out.println("4. List members with the most borrowings.");
                        System.out.println("5. Clear screen");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Choose a sub-option: ");
                        try {
                            reportChoice = scanner.nextInt();
                            switch (reportChoice) {
                                case 1:
                                    LM.viewCurrentlyBorrowedBooks();
                                    break;
                                case 2:
                                    LM.viewOverdueBooks();
                                    break;
                                case 3:
                                    LM.viewMostPopularBooks();
                                    break;
                                case 4:
                                    LM.viewMemberWithTheMostBorrowing();
                                    break;
                                case 5:    
                                    Utility.clearScreen();
                                    break;
                                case 6:
                                    System.out.println("Returning to Main Menu...");
                                default:
                                    System.out.println("\nInvalid sub-option! Choose between 1 and 6.");
                            }
                        }catch (InputMismatchException e) {
                            System.out.println("\nError: Invalid input. Please enter a number.");
                            scanner.next();
                            reportChoice = 0;
                        }
                    } while (reportChoice != 6);
                    break;
                case 5:    
                    Utility.clearScreen();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("\nInvalid option! Please choose between 1 and 6.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: Invalid input. Please enter a number.\n");
                scanner.next();
                choice = 0;
            }
        } while (choice != 6);
        scanner.close();
    }
}
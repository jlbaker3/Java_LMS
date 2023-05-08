package model;

import DataAccessObjects.BookDAO;
import DataAccessObjects.TransactionDAO;
import DataAccessObjects.UserDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Library {
    private final int MAX_BOOK_LIMIT = 3;
    private final int MAX_LOAN_DAYS = 14;

    //public ArrayList<Transaction> transactions;
    //public ArrayList<User> users;
    //public ArrayList<Book> books;
    public TransactionDAO transactions;
    public UserDAO users;
    public BookDAO books;
    public ArrayList<String> msgLog;

        /*
        Purpose: create a new instance of the library class/ setup arraylists
         */
    public Library() throws SQLException {
        //transactions = new ArrayList<>();
        //users = new ArrayList<>();
        //books = new ArrayList<>();
        transactions = new TransactionDAO("jdbc:sqlite:dbLIB.db");
        users = new UserDAO("jdbc:sqlite:dbLIB.db");
        books = new BookDAO("jdbc:sqlite:dbLIB.db");
        msgLog = new ArrayList<>();
    }


    /*
    Purpose: Add a new user to the arraylist managed by the library class

    Params:
    u - model.User object to be added to the list
     */
    public void addUser(User u) throws SQLException {
        users.insert(u);
    }

    /*
    Purpose: Add a new book to the arraylist managed by the library class

    Params:
    b - model.Book object to be added to the list
     */
    public void addBook(Book b) throws SQLException {
        books.insert(b);
    }

    /*
    Purpose: Add a new transaction to the arraylist managed by the library class

    Params:
    t - model.Transaction object to be added to the list
     */
    public void addTransaction(Transaction t) throws SQLException {
        transactions.insert(t);
    }

    /*
    Purpose: Check if a book is currently available to be borrowed

    Params:
    bookID - Integer that refers to the id of the book to be checked

    return: Boolean whether the book is available or not
     */
    public boolean isAvailable(int bookID) throws SQLException {
        //Check if book with given id exists
        for(Book b : this.books.getAll()){
            if(b.getID() == bookID){
                //Loop through transactions to check if book has a transaction status of true
                for(Transaction t : this.transactions.getAll()){
                    if((t.getBookID() == bookID) && (t.isStatus() == true)){
                        return false;
                    }
                }
                //model.Book did not have an active transaction
                return true;
            }
        }
        //model.Book was not found in library
        return false;
    }

    /*
    Purpose: Get the number of books a single user has checked out

    Params:
    userID - Integer that refers to the id of the user

    return: Number of books currently borrowed
     */
    public int getBorrowCount(int userID) throws SQLException {
        //cntr used to track how many books this user has borrowed
        int cntr = 0;

        //Check if user with given id exists
        for(User u : this.users.getAll()){
            if(u.getID() == userID){
                //Loop through transations and check if any books are currently borrowed, if yes, increment cntr
                for(Transaction t : this.transactions.getAll()){
                    if((t.getUserID() == userID) && (t.isStatus() == true)){
                        cntr++;
                    }
                }
            }
        }
        return cntr;
    }

    /*
    Purpose: Get the due date of from the date date given based on the max loan date

    Params:
    date - Issue date used to check when the due date would be

    Return: The date when the due date would be
     */
    public LocalDate getDueDate(LocalDate date){
        LocalDate returnDate = date.plusDays(MAX_LOAN_DAYS);

        return returnDate;
    }

    /*
    Purpose: Used to issue a book to a user

    Params:
    userID - Integer that refers to the id of the user borrowing the book
    bookID - Integer that refers to the id of the book being borrowed

    return: Boolean the represents whether the process was successful or not
     */
    public boolean issueBook(int userID, int bookID) throws SQLException {
        if((isAvailable(bookID)) && (getBorrowCount(userID) < MAX_BOOK_LIMIT))
            for(User u : this.users.getAll()){
                if((u.getID() == userID) && (u.getBalance() == 0.0)){
                    Transaction t = new Transaction(bookID, userID);
                    t.setIssueDate(LocalDate.now());
                    addTransaction(t);
                    //transactions.add(t);
                    return true;
                }
            }


        return false;
    }

    /*
    Purpose: Used to return a book that has been issued, also sets the balance if a book is returned late

    Params:
    bookID - Integer that refers to the id of the book to be returned

    return: Boolean the represents whether the process was successful or not
     */
    public boolean returnBook(int bookID) throws SQLException {
        for(Transaction t : this.transactions.getAll()){
            if((t.getBookID() == bookID) && (t.isStatus() == true)){
                t.setStatus(false);
                transactions.update(t);

                //check if book is returned late
                if(LocalDate.now().isAfter(getDueDate(t.getIssueDate()))) {
                    //find user and set their balance
                    for(User u : this.users.getAll()){
                        if(u.getID() == t.getUserID()) {
                            u.setBalance(computeFine(t));
                            users.update(u);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    /*
    Purpose: Used to compute the fine if a book is returned late

    Params:
    t - A transaction object used to compute the amount of days overdue

    return: Fine of overdue return
     */
    private double computeFine(Transaction t) {
        double fine = 0.0;

        //find the number of days between the return date and now, then calculate the fine total
        int diff = Period.between(getDueDate(t.getIssueDate()), LocalDate.now()).getDays();
        fine = 1.00 * diff;

        return fine;
    }



    /*
    Purpose: Used to search for books that have a title that match the string given

    Params:
    key - String that contains the phrase to be matched to the book title

    return: ArrayList of books that have titles matching the phrase given
     */
    public ArrayList<Book> searchBook(String key) throws SQLException {
        ArrayList<Book> bList = new ArrayList<>();

        for(Book b : this.books.getAll()){
            if(b.getName().toLowerCase().contains(key.toLowerCase()))
                bList.add(b);
        }

        return bList;
    }

    /*
    Purpose: Used to collect the fines of a user

    Params:
    u - user object that has a fine
     */
    public void collectFine(User u){
        u.setBalance(0);
    }

    /*
    Purpose: Used to get a book object from the ArrayList of books

    Params:
    bookID - Integer that refers to the id of the book to be returned

    return: model.Book object found
     */
    public Book getBook(int bookID) throws SQLException {
        Book b1 = null;
        for(Book b : this.books.getAll()){
            if(b.getID() == bookID)
                b1 = b;
        }
        return b1;
    }

    /*
    Purpose: Used to get a user object from the ArrayList of users

    Params:
    userID - Integer that refers to the id of the user to be returned

    return: model.User object found
     */
    public User getUser(int userID) throws SQLException {
        User u1 = null;
        for(User u : this.users.getAll()){
            if(u.getID() == userID)
                u1 = u;
        }
        return u1;
    }
}

package model;

import java.time.LocalDate;

public class Transaction {
    private int bookID;
    private int userID;
    private LocalDate issueDate;
    private boolean status;

        /*
        Purpose: create a new instance of the model.Transaction class

        params:
        bookID - Integer referring to the bookID being borrowed
        userID - Integer referring to the userID borrowing the book
         */
    public Transaction(int bookID, int userID) {
        this.bookID = bookID;
        this.userID = userID;

        //set the status to true when a book is borrowed
        this.status = true;
    }
    public Transaction(int bookID, int userID, LocalDate issueDate, boolean status){
        this.bookID = bookID;
        this.userID = userID;
        this.issueDate = issueDate;
        this.status = status;
    }


    /*
    Purpose: used to get bookID of the transaction
    return: integer that represents the bookID of the transation
     */
    public int getBookID() {
        return bookID;
    }

    /*
    Purpose: used to get userID of the transaction
    return: integer that represents the userID of the transaction
     */
    public int getUserID() {
        return userID;
    }

    /*
    Purpose: used to get date the transaction was issued
    return: LocalDate that represents the issueDate of the transaction
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /*
    Purpose: used to get the status of a transaction
    return: Boolean that represents if a transaction is active
    */
    public boolean isStatus() {
        return status;
    }



    /*
    Purpose: used to set the bookId of a transaction
    Params:
    BookID - integer representing the books id
     */
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /*
    Purpose: used to set the usersID of a transaction
    Params:
    userID - Integer representing the users id
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /*
    Purpose: used to set the issue date of a transaction
    Params:
    issueDate - LocalDate representing the issue date of a transaction
     */
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    /*
    Purpose: used to set the status of a transaction
    Params:
    status - Boolean representing the status of a transaction
     */
    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "model.Transaction{bookID: "+ bookID+ ", userID: "+userID+ ", IssueDate= "+issueDate+ ", Status= "+status+"}";
    }
}

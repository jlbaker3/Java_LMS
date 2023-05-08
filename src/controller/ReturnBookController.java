package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Book;
import model.Library;
import model.Transaction;
import model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ReturnBookController {

    Library lib;
    public void initLib(Library lib) throws SQLException {
        //Get a reference to the library object passed from the main class
        this.lib = lib;

        //Method used to populate the book list
        populateBookList();
    }

    public void initialize() {
        //Textbox changeListener called when the text in the book search is changed
        //We use the library searchBook function to find an arraylist of books that have a title matching the new string
        //populate the listview with the new arrayList returned from the function
        txtBookSearch.textProperty().addListener((observableValue, oldString, newString) -> {
//            lstBook.setItems(FXCollections.observableArrayList(lib.searchBook(newString)));
            try {
                lstBook.setItems(FXCollections.observableArrayList(lib.books.getByQuery(newString)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        //Listview listener used to detect when the user clicks on a list item in the book list
        //Check if the new book object passed is null, if no, textbox is set to the books id
        lstBook.getSelectionModel().selectedItemProperty().addListener((observableValue, oldBook, newBook) -> {
            if(newBook != null){
                txtBookId.setText(""+newBook.getID());
            }
        });
    }

    @FXML
    private Button btnReturn;

    @FXML
    private Label lblMessage;

    @FXML
    private ListView<Book> lstBook;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookSearch;

    @FXML
    void IssueBook(ActionEvent event) throws SQLException {
        //Get the book object using the id in the textbox
        Book b = lib.getBook(Integer.parseInt(txtBookId.getText()));
        Transaction t = null;
        User u = null;

        //Get the transaction and user objects using the bookID
        for(Transaction t1: lib.transactions.getAll()){
            if((b.getID() == t1.getBookID()) && (t1.isStatus())) {
                t = t1;
                u = lib.getUser(t.getUserID());
            }
        }

        //Check if the book is available, if yes, then it cannot be returned
        if(lib.isAvailable(b.getID())){
            //Set the text color to red and display error message
            lblMessage.setStyle("-fx-text-fill: #ea4532");
            lblMessage.setText("Book currently not borrowed");
        }
        //else, we call the returnBook function, this returns the book and returns a boolean that represents whether the
        //operation was successful
        else if(lib.returnBook(b.getID())){
            //Set the text color to green
            lblMessage.setStyle("-fx-text-fill: #a7ef6f");

            //Check if the book is returned late to display the fine
            if(LocalDate.now().isAfter(lib.getDueDate(t.getIssueDate()))) {
                //Get how many days late the book was returned
                int daysLate = Period.between(lib.getDueDate(t.getIssueDate()), LocalDate.now()).getDays();
                lblMessage.setText("You returned "+ b.getName()+ " "+daysLate+" days late! \nYour outstanding balance is "
                        +String.format("%,.2f",u.getBalance()));
            }
            else
                lblMessage.setText("Book returned successfully!");

        }
    }



    public void populateBookList() throws SQLException {
        //Create an arraylist to hold issued books
        //Loop through all books, if book is not available we add it to the arraylist
        ArrayList<Book> bList = new ArrayList<>();
        for(Book b: lib.books.getAll()){
            //if(!lib.isAvailable(b.getID()))
                bList.add(b);
        }

        ObservableList<Book> obsBook = FXCollections.observableArrayList(bList);
        lstBook.setItems(obsBook);
    }
}

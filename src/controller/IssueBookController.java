package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class IssueBookController {

    Library lib;
    public void initLib(Library lib) throws SQLException {
        //Get a reference to the library object passed from the main class
        this.lib = lib;


//        ArrayList<User> uList = new ArrayList<>();
//        for(User u: lib.users)
//            uList.add(u);

        ObservableList<User> obsUser = FXCollections.observableArrayList(lib.users.getAll());
        lstUser.setItems(obsUser);

//        ArrayList<Book> bList = new ArrayList<>();
//        for(Book b: lib.books)
//            bList.add(b);

        ObservableList<Book> obsBook = FXCollections.observableArrayList(lib.books.getAll());
        lstBook.setItems(obsBook);
    }

    public void initialize(){
        //Textbox changeListener called when the text in the book search is changed
        //We use the library searchBook function to find an arraylist of books that have a title matching the new string
        //poppulate the listview with the new arrayList returned from the function
        txtBookSearch.textProperty().addListener((observableValue, oldString, newString) -> {
            try {
                lstBook.setItems(FXCollections.observableArrayList(lib.books.getByQuery(newString)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        //textbox change listener called when the text in the user search is changed
        //We iterate through the user list in the lib object, if the user's name contains the new string it is added to a new arraylist
        //the new arraylist is displayed in teh list view
        txtUserSearch.textProperty().addListener((observableValue, oldString, newString) -> {
//            ArrayList<User> uList = new ArrayList<>();
//            for(User u: lib.users){
//                if(u.getName().toLowerCase().contains(newString.toLowerCase()))
//                    uList.add(u);
//            }
            try {
                lstUser.setItems(FXCollections.observableArrayList(lib.users.getByQuery(newString)));
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

        //Listview listener used to detect when the user clicks on a list item in the user list
        //Check if the new user object is null, if no, the textbox is set to the users id
        lstUser.getSelectionModel().selectedItemProperty().addListener((observableValue, oldUser, newUser) -> {
            if(newUser != null){
                txtUserId.setText(""+newUser.getID());
            }
        });
    }

    @FXML
    private Button btnIssue;

    @FXML
    private ListView<Book> lstBook;

    @FXML
    private ListView<User> lstUser;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookSearch;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserSearch;

    @FXML
    private Label lblMessage;

    @FXML
    void IssueBook(ActionEvent event) throws SQLException {
        //Get the book and user object using the ids in the textbox
        Book b = lib.getBook(Integer.parseInt(txtBookId.getText()));
        User u = lib.getUser(Integer.parseInt(txtUserId.getText()));

        //call issueBook, this issues the book to the user if possible, and returns a boolean the represents whether the operation
        //was successful
        Boolean issue = lib.issueBook(u.getID(), b.getID());

        //Check if the book was issued successfully
        if(issue){
            //If successful, set the label text color to green and show a confirmation message with the due date
            lblMessage.setStyle("-fx-text-fill: #a7ef6f");
            lblMessage.setText(b.getName() + " has been issued to " + u.getName() + "\nThe due date is " + lib.getDueDate(LocalDate.now()));
        }
        else{
            //if not successful, change the text color to red
            lblMessage.setStyle("-fx-text-fill: #ea4532");

            //Check the book availability and set message to unavailable
            if(!lib.isAvailable(b.getID())){
                lblMessage.setText("This book is currently unavailable!");
            }
            //Check the users balance and set the message to show an outstanding balance
            else if(u.getBalance() > 0){
                lblMessage.setText("User has an outstanding balance of $" + String.format("%,.2f",u.getBalance()));
            }
            //check the users borrow count and set the message to show that the user hit the borrow limit
            else if(lib.getBorrowCount(u.getID()) >= 3){
                lblMessage.setText("User has reached the maximum limit of borrowed books!");
            }
        }

    }

}

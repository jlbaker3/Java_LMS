package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Book;
import model.Library;
import model.Transaction;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDetailsController {

    Library lib;
    User u1;
    public void initLib(Library lib) throws SQLException {
        //Get a reference to the library object passed from the main class
        this.lib = lib;

        //combo box is populated here with the list of users
        ArrayList<User> uList = lib.users.getAll();
//        for(User u: lib.users)
//            uList.add(u);

        ObservableList<User> obsUser = FXCollections.observableArrayList(uList);
        cmbUsers.setItems(obsUser);
    }

    public void initialize(){

        //Combobox listener that runs when the selected item changes
        //listener gives the user object that is selected, and the data is put into the labels
        cmbUsers.getSelectionModel().selectedItemProperty().addListener((observableValue, userOld, userNew) -> {
            //populate labels with user info
            lblName.setText(userNew.getName());
            lblEmail.setText(userNew.getEmail());
            lblAddress.setText(userNew.getAddress());
            lblBirthday.setText(userNew.getDateOfBirth().toString());
            lblBalance.setText(String.format("%,.2f",userNew.getBalance()));

            //check is user is a student or faculty
            if(userNew.getStudent())
                lblType.setText("Student");
            else
                lblType.setText("Faculty");

            //Check user balance if greater than 0, change balance text to red, show collect fine button, and save the user
            if(userNew.getBalance() > 0) {
                lblBalance.setStyle("-fx-text-fill: #ea4532");
                btnCollect.setVisible(true);
                u1 = userNew;
            }
            else{
                lblBalance.setStyle("-fx-text-fill: #3ec152");
                btnCollect.setVisible(false);
            }

            ArrayList<Book> bList = new ArrayList<>();
            try {
                for(Transaction t: lib.transactions.getAll()){
                    if(t.getUserID() == userNew.getID() && (t.isStatus())){
                        bList.add(lib.getBook(t.getBookID()));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ObservableList<Book> obsBook = FXCollections.observableArrayList(bList);
            lstBooks.setItems(obsBook);
        });


    }



    @FXML
    private Button btnCollect;

    @FXML
    private ComboBox<User> cmbUsers;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblBirthday;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblName;

    @FXML
    private Label lblType;

    @FXML
    private Label lblBalance;

    @FXML
    private ListView<Book> lstBooks;

    @FXML
    void CollectFine(ActionEvent event) {
        //collect fine and update balance label
        lib.collectFine(u1);
        lblBalance.setText(String.format("%,.2f",u1.getBalance()));

        //reset balance text color and u1 variable, hide the collect fine button
        lblBalance.setStyle("-fx-text-fill: #3ec152");
        btnCollect.setVisible(false);
        u1 = null;

    }

}

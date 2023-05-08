package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Library;
import model.Transaction;
import model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class IssuedBooksTableController {

    Library lib;
    public void initLib(Library lib) throws SQLException {
        //Get a reference to the library object passed from the main class
        this.lib = lib;

        //Create an arraylist and populate it with books currently borrowed
        ArrayList<Transaction> tList = lib.transactions.getCurrent();
//        for(Transaction t: lib.transactions.getAll()){
//            if(t.isStatus())
//                tList.add(t);
//        }

        //Set the arraylist to the table view
        ObservableList<Transaction> obsTransaction = FXCollections.observableArrayList(tList);
        tblRecords.setItems(obsTransaction);

        //Associate the table view columns with the transaction properties
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));

    }

    @FXML
    private TableColumn<Transaction, Integer> colBookID;

    @FXML
    private TableColumn<Transaction, LocalDate> colIssueDate;

    @FXML
    private TableColumn<Transaction, Integer> colUserID;

    @FXML
    private TableView<Transaction> tblRecords;

}

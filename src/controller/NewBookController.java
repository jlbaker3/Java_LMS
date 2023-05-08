package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import model.Library;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewBookController {

    Library lib;
    public void initLib(Library lib){
        //Get a reference to the library object passed from the main class
        this.lib = lib;

    }

    public void initialize(){
        //Initialize the listview and the combobox with default values using observable lists

        ArrayList<String> pList = new ArrayList<>();
        pList.add("Allen and Unwin");
        pList.add("Disney");
        pList.add("Pearson");
        pList.add("Penguin");
        pList.add("Macmillan");
        ObservableList<String> obsPublisher = FXCollections.observableArrayList(pList);
        lstPublisher.setItems(obsPublisher);


        ArrayList<String> gList = new ArrayList<>();
        gList.add("Education");
        gList.add("Adventure");
        gList.add("Thriller");
        gList.add("History");
        ObservableList<String> obsGenre = FXCollections.observableArrayList(gList);
        cmbGenre.setItems(obsGenre);

    }




    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private ListView<String> lstPublisher;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtYear;

    @FXML
    void registerUser(ActionEvent event) throws SQLException {
        //Create a new book using the data from the form and add it to the libraries list of books
        Book b = new Book(txtName.getText(), txtAuthor.getText(), lstPublisher.getSelectionModel().getSelectedItem(),
                cmbGenre.getSelectionModel().getSelectedItem(), txtISBN.getText(),Long.parseLong(txtYear.getText()));
        lib.books.insert(b);


        //Get a reference to the stage then close it when finished, show alert box to confirm book creation
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Registration Successful");
        a.setHeaderText("Registered "+b.getName());
        a.show();

        stage.hide();
    }

}

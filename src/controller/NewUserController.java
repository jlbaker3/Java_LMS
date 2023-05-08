package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Library;
import model.User;

import java.sql.SQLException;
import java.time.LocalDate;

public class NewUserController {

    Library lib;
    public void initLib(Library lib){
        //Get a reference to the library object passed from the main class
        this.lib = lib;
    }

    @FXML
    private DatePicker dtpBirth;

    @FXML
    private RadioButton rdoFaculty;

    @FXML
    private RadioButton rdoStudent;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    void registerUser(ActionEvent event) throws SQLException {
        //Create a new user with the values from the form and add it to the libraries list of users
        User u = new User(txtName.getText(),txtEmail.getText(),txtAddress.getText(),dtpBirth.getValue(),rdoStudent.isSelected());
        lib.users.insert(u);


        //Get a reference to the stage then close it when finished, show alert box to confirm user creation
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Registration Successful");
        a.setHeaderText("Registered "+u.getName());
        a.show();

        stage.hide();
    }

}

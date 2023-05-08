package controller;

import com.project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Library;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class LibraryController {
    Library lib;
    public void initLib(Library lib){
        this.lib = lib;
    }

    @FXML
    void issueBook(ActionEvent event) throws IOException, SQLException {
        Stage issueBookStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/issuebook-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        issueBookStage.setTitle("Issue Book to User");
        issueBookStage.setScene(scene);

        IssueBookController controller = fxmlLoader.getController();
        controller.initLib(lib);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        issueBookStage.initOwner(thisStage);
        issueBookStage.initModality(Modality.WINDOW_MODAL);

        issueBookStage.show();

    }

    @FXML
    void newBook(ActionEvent event) throws IOException {
        Stage newBookStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/newbook-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newBookStage.setTitle("Add New Book");
        newBookStage.setScene(scene);

        NewBookController controller = fxmlLoader.getController();
        controller.initLib(lib);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        newBookStage.initOwner(thisStage);
        newBookStage.initModality(Modality.WINDOW_MODAL);

        newBookStage.show();
    }

    @FXML
    void newUser(ActionEvent event) throws IOException {
        Stage newUserStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/newuser-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        newUserStage.setTitle("Add New User");
        newUserStage.setScene(scene);

        NewUserController controller = fxmlLoader.getController();
        controller.initLib(lib);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        newUserStage.initOwner(thisStage);
        newUserStage.initModality(Modality.WINDOW_MODAL);

        newUserStage.show();
    }

    @FXML
    void returnBook(ActionEvent event) throws IOException, SQLException {
        Stage returnBookStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/returnbook-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        returnBookStage.setTitle("Return Book");
        returnBookStage.setScene(scene);

        ReturnBookController controller = fxmlLoader.getController();
        controller.initLib(lib);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        returnBookStage.initOwner(thisStage);
        returnBookStage.initModality(Modality.WINDOW_MODAL);

        returnBookStage.show();
    }

    @FXML
    void viewIssued(ActionEvent event) throws IOException, SQLException {
        System.out.println("viewIssued");

        Stage issuedBooksStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/issuedbookstable-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        issuedBooksStage.setTitle("View Issued Books");
        issuedBooksStage.setScene(scene);

        IssuedBooksTableController controller = fxmlLoader.getController();
        controller.initLib(lib);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        issuedBooksStage.initOwner(thisStage);
        issuedBooksStage.initModality(Modality.WINDOW_MODAL);

        issuedBooksStage.show();
    }

    @FXML
    void viewUser(ActionEvent event) throws IOException, SQLException {
        Stage viewUserStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/userdetails-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        viewUserStage.setTitle("User Details");
        viewUserStage.setScene(scene);

        UserDetailsController controller = fxmlLoader.getController();
        controller.initLib(lib);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        viewUserStage.initOwner(thisStage);
        viewUserStage.initModality(Modality.WINDOW_MODAL);

        viewUserStage.show();
    }

}

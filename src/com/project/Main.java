package com.project;

import controller.LibraryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../../view/library-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        LibraryController controller = fxmlLoader.getController();
        Library lib = new Library();
//        User u1 = new User("John Stones","jstones@gmail.com","211 Ryder St. Tallahassee, FL 32304", LocalDate.of(1989,06,13), true);
//        User u2 = new User("Jack Bauer","jack24@gmail.com","7400 Bay Rd. University Center, MI 48710",LocalDate.of(1988,11,15),false);
//        User u3 = new User("Harry Kane","hkane@gmail.com","123 James Boyd Rd. Scranton, PA 28410",LocalDate.of(1988,2,1), false);
//        User u4 = new User("Tim Arnold","ta123@gmail.com","3412 Dinsmore Ave, MA 01710",LocalDate.of(1999,1,15), true);
//        lib.addUser(u1);
//        lib.addUser(u2);
//        lib.addUser(u3);
//        lib.addUser(u4);
//        //u1.setBalance(10);
//
//        Book b1 = new Book("Programming with Java","Daniel Liang","Pearson","Education","1234568924",2020);
//        Book b2 = new Book("Data Structures and Algorithms","Robert Lafore","Pearson","Education","98726213",2001);
//        Book b3 = new Book("Harry Potter and The Chamber of Secrets","J.K. Rowling","Scholastic","Adventure","343255323",1998);
//        Book b4 = new Book("Lord of the Rings - The Two Towers","Tolkien","Wiley","Thriller","989636362",1945);
//        lib.addBook(b1);
//        lib.addBook(b2);
//        lib.addBook(b3);
//        lib.addBook(b4);

        controller.initLib(lib);

        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

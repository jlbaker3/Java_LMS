package DataAccessObjects;

import model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
    Connection connection;

    public BookDAO(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public void insert(Book b) throws SQLException {
        PreparedStatement psInsert = connection.prepareStatement("Insert into books (name,author,publisher,genre,ISBN,year) Values(?,?,?,?,?,?)");
        psInsert.setString(1,b.getName());
        psInsert.setString(2,b.getAuthor());
        psInsert.setString(3,b.getPublisher());
        psInsert.setString(4,b.getGenre());
        psInsert.setString(5,b.getISBN());
        psInsert.setInt(6, (int) b.getYear());
        psInsert.executeUpdate();
    }

    public void delete(Book b) throws SQLException {
        PreparedStatement psDelete = connection.prepareStatement("Delete from books where ID=?");
        psDelete.setInt(1,b.getID());
        psDelete.executeUpdate();
    }

    public void update(Book b) throws SQLException {
        PreparedStatement psUpdate = connection.prepareStatement("Update books Set name=?,author=?,publisher=?,genre=?,isbn=?," +
                "year=? Where ID=?");
        psUpdate.setString(1,b.getName());
        psUpdate.setString(2,b.getAuthor());
        psUpdate.setString(3,b.getPublisher());
        psUpdate.setString(4,b.getGenre());
        psUpdate.setString(5,b.getISBN());
        psUpdate.setInt(6, (int) b.getYear());
        psUpdate.setInt(7,b.getID());
        psUpdate.executeUpdate();
    }

    public ArrayList<Book> getAll() throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From books");
        ResultSet rs = psGet.executeQuery();

        ArrayList<Book> bList = new ArrayList<>();
        while(rs.next()){
            bList.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), (long) rs.getInt(7)));
        }
        return bList;
    }

    public ArrayList<Book> getByQuery(String s) throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From books Where name Like ?");
        psGet.setString(1,"%"+s+"%");
        ResultSet rs = psGet.executeQuery();

        ArrayList<Book> bList = new ArrayList<>();
        while(rs.next()){
            bList.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), (long) rs.getInt(7)));
        }
        return bList;
    }

    public Book getBook(int id) throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From books Where ID=?");
        psGet.setInt(1,id);
        ResultSet rs = psGet.executeQuery();

        return new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5), rs.getString(6), (long) rs.getInt(7));
    }
}

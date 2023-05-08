package DataAccessObjects;

import model.Book;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;

public class TransactionDAO {
    Connection connection;

    public TransactionDAO(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public void insert(Transaction t) throws SQLException {
        PreparedStatement psInsert = connection.prepareStatement("Insert into libTransactions Values(?,?,?,?)");
        psInsert.setInt(1,t.getBookID());
        psInsert.setInt(2,t.getUserID());
        psInsert.setDate(3,java.sql.Date.valueOf(t.getIssueDate()));
        psInsert.setBoolean(4,t.isStatus());

        psInsert.executeUpdate();
    }

    public void delete(Transaction t) throws SQLException {
        PreparedStatement psDelete = connection.prepareStatement("Delete from libTransactions where bookID=? And userID=? And issueDate=?");
        psDelete.setInt(1, t.getBookID());
        psDelete.setInt(2,t.getUserID());
        psDelete.setDate(3,java.sql.Date.valueOf(t.getIssueDate()));
        psDelete.executeUpdate();
    }

    public void update(Transaction t) throws SQLException {
        PreparedStatement psUpdate = connection.prepareStatement("Update libTransactions Set bookID=?,userID=?,issueDate=?,status=? " +
                "Where bookID=? And userID=? And issueDate=?");
        psUpdate.setInt(1,t.getBookID());
        psUpdate.setInt(2,t.getUserID());
        psUpdate.setDate(3,java.sql.Date.valueOf(t.getIssueDate()));
        psUpdate.setBoolean(4,t.isStatus());
        psUpdate.setInt(5,t.getBookID());
        psUpdate.setInt(6,t.getUserID());
        psUpdate.setDate(7,java.sql.Date.valueOf(t.getIssueDate()));
        psUpdate.executeUpdate();
    }

    public ArrayList<Transaction> getAll() throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From libTransactions");
        ResultSet rs = psGet.executeQuery();

        ArrayList<Transaction> tList = new ArrayList<>();
        while(rs.next()){
            tList.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(),
                    rs.getBoolean(4)));
        }
        return tList;
    }

    public ArrayList<Transaction> getCurrent() throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From libTransactions Where status=1");
        ResultSet rs = psGet.executeQuery();

        ArrayList<Transaction> tList = new ArrayList<>();
        while(rs.next()){
            tList.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(),
                    rs.getBoolean(4)));
        }
        return tList;
    }

    public ArrayList<Transaction> getByUser(int id) throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From libTransactions Where userID=?");
        psGet.setInt(1,id);
        ResultSet rs = psGet.executeQuery();

        ArrayList<Transaction> tList = new ArrayList<>();
        while(rs.next()){
            tList.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(),
                    rs.getBoolean(4)));
        }
        return tList;
    }

    public ArrayList<Transaction> getByBook(int id) throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From libTransactions Where bookID=?");
        psGet.setInt(1,id);
        ResultSet rs = psGet.executeQuery();

        ArrayList<Transaction> tList = new ArrayList<>();
        while(rs.next()){
            tList.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(),
                    rs.getBoolean(4)));
        }
        return tList;
    }
}

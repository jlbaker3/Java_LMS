package DataAccessObjects;

import model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    Connection connection;

    public UserDAO(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public void insert(User u) throws SQLException {
        PreparedStatement psInsert = connection.prepareStatement("Insert into users (name,email,address,dateOfBirth,isStudent,balance) Values(?,?,?,?,?,?)");
        psInsert.setString(1,u.getName());
        psInsert.setString(2,u.getEmail());
        psInsert.setString(3,u.getAddress());
        psInsert.setDate(4,java.sql.Date.valueOf(u.getDateOfBirth()));
        psInsert.setBoolean(5,u.getStudent());
        psInsert.setDouble(6,u.getBalance());
        psInsert.executeUpdate();
    }

    public void delete(User u) throws SQLException {
        PreparedStatement psDelete = connection.prepareStatement("Delete from users where ID=?");
        psDelete.setInt(1,u.getID());
        psDelete.executeUpdate();
    }

    public void update(User u) throws SQLException {
        PreparedStatement psUpdate = connection.prepareStatement("Update users Set name=?,email=?,address=?,dateOfBirth=?,isStudent=?," +
                "balance=? Where ID=?");
        psUpdate.setString(1,u.getName());
        psUpdate.setString(2,u.getEmail());
        psUpdate.setString(3,u.getAddress());
        psUpdate.setDate(4,java.sql.Date.valueOf(u.getDateOfBirth()));
        psUpdate.setBoolean(5,u.getStudent());
        psUpdate.setDouble(6,u.getBalance());
        psUpdate.setInt(7,u.getID());
        psUpdate.executeUpdate();
    }

    public ArrayList<User> getAll() throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From users");
        ResultSet rs = psGet.executeQuery();

        ArrayList<User> uList = new ArrayList<>();
        while(rs.next()){
            uList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getDate(5).toLocalDate(), rs.getBoolean(6), rs.getDouble(7)));
        }
        return uList;
    }

    public ArrayList<User> getByQuery(String s) throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From users Where name Like ?");
        psGet.setString(1,"%"+s+"%");
        ResultSet rs = psGet.executeQuery();

        ArrayList<User> uList = new ArrayList<>();
        while(rs.next()){
            uList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getDate(5).toLocalDate(), rs.getBoolean(6), rs.getDouble(7)));
        }
        return uList;
    }

    public User getUser(int id) throws SQLException {
        PreparedStatement psGet = connection.prepareStatement("Select * From users Where ID=?");
        psGet.setInt(1,id);
        ResultSet rs = psGet.executeQuery();

        User temp = null;
        while(rs.next()){
            temp = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getDate(5).toLocalDate(), rs.getBoolean(6), rs.getDouble(7));
        }
        return temp;
    }
}

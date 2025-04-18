package group31.classes.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;


public class UserDAO {
    public Connection connection;

    //constructor
    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password, email, phonenumber, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getRole());
            statement.executeUpdate();
            System.out.println("Account Registered!");
        }  catch (Exception e) {
            System.err.println("Database connection failed");
        }
    }

    public User getUser(String username, String password) throws SQLException {
        User loggedUser = new User();
        String sql = "SELECT * FROM users";
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {

            while (result.next()) {
                if (result.getString("username").equals(username)
                    && BCrypt.checkpw(password, result.getString("password"))) {
                    loggedUser = new User(result.getString("username"),
                    password,
                    result.getString("email"),
                    result.getString("phoneNumber"),
                    result.getString("address"),
                    result.getString("role"));
                    break;
                }
            }
            return loggedUser;
        } catch (Exception e) {
            System.err.println("Database connection failed");
        }
        return loggedUser;
    }

    public User getUser(String username) throws SQLException {
        User loggedUser = new User();
        String sql = "SELECT * FROM users";
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {

            while (result.next()) {
                if (result.getString("username").equals(username)) {
                    loggedUser = new User(result.getString("username"),
                    result.getString("password"),
                    result.getString("email"),
                    result.getString("phoneNumber"),
                    result.getString("address"),
                    result.getString("role"));
                    break;
                }
            }
            return loggedUser;
        } catch (Exception e) {
            System.err.println("Database connection failed");
        }
        return loggedUser;
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {

                while (result.next()) {
                    User listUser = new User(result.getString("username"),
                    result.getString("password"),
                    result.getString("email"),
                    result.getString("phoneNumber"),
                    result.getString("address"),
                    result.getString("role"));
                    userList.add(listUser);
                }

            return userList;
        } catch (Exception e) {
            System.err.println("Database connection failed");  
            return userList; 
        }
    }
}

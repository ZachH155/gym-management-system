package group31;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Keyin2021";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

package kiosk.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* Adapter class to access the .db file */
public class Database {
    private Connection connection;

    /* Constructor */
    public Database(String filePath) {
        Connection connection = null;
        try { connection = DriverManager.getConnection(filePath); }
        catch (SQLException e) { e.printStackTrace(); }

        this.connection = connection;
    }

    /* Close Connection */
    public void closeConnection() {
        try { this.connection.close(); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    Statement makeStatement() {
        Statement statement = null;
        try { statement = this.connection.createStatement(); }
        catch (SQLException e) { e.printStackTrace(); }

        return statement;
    }
}
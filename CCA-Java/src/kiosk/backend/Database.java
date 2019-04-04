package kiosk.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* Adapter class to access the .db file */
public class Database {
    private Connection connection; // creates a single database connection that will be referenced the entire time the kiosk program is on (per instance).

    /* Constructor */
    public Database(String filePath) {
        Connection connection = null; // create connection variable as a null connection to be used if connection fails
        try { connection = DriverManager.getConnection(filePath); } // attempt to connect to database defined by argument filePath
        catch (SQLException e) { e.printStackTrace(); } // if failed to connect, error printStackTrace

        this.connection = connection; // if connection successful, save the connection in class attribute
    }

    /* Close Connection */
    public void closeConnection() {
        try { this.connection.close(); } // attempt to close class attribute connection to database
        catch (SQLException e) { e.printStackTrace(); } // if close fails, error printStackTrace
    }

    Statement makeStatement() {
        Statement statement = null; // create statement variable as a null statement allowing for a generic return statement
        try { statement = this.connection.createStatement(); } // attempt to create a statement from database connection
        catch (SQLException e) { e.printStackTrace(); } // if the statement creation fails, error printStackTrace

        return statement; // return either null, or the newly created return statement
    }
}

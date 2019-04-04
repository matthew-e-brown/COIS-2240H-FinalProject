package kiosk.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The <code>Database</code> class allows connections to the
 * database to be opened and closed. A connection to
 * <code>master.db</code> is created at the start of the program,
 * and is closed at the end of the program.
 */
public class Database {
    /**
     * The connection to the database.
     */
    private Connection connection;

    /**
     * Constructor which creates a <code>Connection</code> to the database specified by the <code>filepath</code>.
     * @param filePath A String containing the filepath to the desired database file.
     */
    public Database(String filePath) {
        Connection connection = null; // create connection variable as a null connection to be used if connection fails
        try { connection = DriverManager.getConnection(filePath); } // attempt to connect to database defined by argument filePath
        catch (SQLException e) { e.printStackTrace(); } // if failed to connect, error printStackTrace

        this.connection = connection; // if connection successful, save the connection in class attribute
    }

    /**
     * Closes the connection to the database.
     */
    public void closeConnection() {
        try { this.connection.close(); } // attempt to close class attribute connection to database
        catch (SQLException e) { e.printStackTrace(); } // if close fails, error printStackTrace
    }

    /**
     * Creates a <code>Statement</code> object for the database connection. A <code>Statement</code>
     * object is created any time the database is queried, which occurs in the <code>Menu</code> class.
     * @return Statement The generated <code>Statement</code> object for the <code>connection</code> field.
     */
    Statement makeStatement() {
        Statement statement = null; // create statement variable as a null statement allowing for a generic return statement
        try { statement = this.connection.createStatement(); } // attempt to create a statement from database connection
        catch (SQLException e) { e.printStackTrace(); } // if the statement creation fails, error printStackTrace

        return statement; // return either null, or the newly created return statement
    }
}

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
        Connection connection = null;
        try { connection = DriverManager.getConnection(filePath); }
        catch (SQLException e) { e.printStackTrace(); }

        this.connection = connection;
    }

    /**
     * Closes the connection to the database.
     */
    public void closeConnection() {
        try { this.connection.close(); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    /**
     * Creates a <code>Statement</code> object for the database connection. A <code>Statement</code>
     * object is created any time the database is queried, which occurs in the <code>Menu</code> class.
     * @return Statement The generated <code>Statement</code> object for the <code>connection</code> field.
     */
    Statement makeStatement() {
        Statement statement = null;
        try { statement = this.connection.createStatement(); }
        catch (SQLException e) { e.printStackTrace(); }

        return statement;
    }
}
package kiosk;

import java.sql.*;

/* Adapter class to access the .db file */
class Database {
    private Connection connection;

    /* Constructor */
    Database(String filePath) {
        Connection connection = null;
        try { connection = DriverManager.getConnection(filePath); }
        catch (SQLException e) { e.printStackTrace(); }

        this.connection = connection;
    }

    /* Close Connection */
    void closeConnection() {
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
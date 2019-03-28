package kiosk;

import java.sql.*;

/* Adapter class to access the .db file */
class Database {
    String filePath;
    Connection connection;

    /* Constructor */
    Database(String filePath) {
        this.filePath = filePath;
        connectToDB(filePath);
    }

    /* Open Connection */
    void connectToDB(String filePath){
        Connection connection = null;
        try { connection = DriverManager.getConnection(filePath); }
        catch (SQLException e){ System.out.println("Something went wrong: " + e.getMessage()); }

        this.connection = connection;
    }

    /* Close Connection */
    void closeConnection() {
        try { this.connection.close(); }
        catch (SQLException e) { System.out.println("Something went wrong: " + e.getMessage()); }
    }

    Statement makeStatement() {
        Statement statement = null;
        try { statement = this.connection.createStatement(); }
        catch (SQLException e) { System.out.println("Something went wrong: " + e.getMessage()); }

        return statement;
    }
}
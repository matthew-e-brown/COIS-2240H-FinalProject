package kiosk;
import java.sql.*;
public class Database{
    String filepath;
    Connection conn;

    public Database(String filepath) {
        this.filepath = filepath;
        connectToDB(filepath);
    }

    public void connectToDB(String filepath){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(filepath);
        }
        catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        this.conn = conn;
    }

    public Statement makeStatement() {
        Statement statement = null;
        try {
            statement = this.conn.createStatement();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return statement;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
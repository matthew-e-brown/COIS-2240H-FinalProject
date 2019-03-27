package kiosk;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Controller {

    public static Connection connectToDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:menu.db");
            Statement statement = conn.createStatement();
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return conn;
    }

    public static Statement getStatement(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return statement;
    }

    public static void closeConnection(Statement statement, Connection conn) {
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
    public static ArrayList<String> generateTypes(Statement statement){
        ArrayList<String> types = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT DISTINCT type FROM menu");
            while (rs.next()) {
                types.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return types;
    }

}

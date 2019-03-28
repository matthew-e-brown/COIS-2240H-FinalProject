package kiosk;
import java.sql.*;

public class Order {
    public static void addToOrder(Database DB, String name, float price) {
        try {
            Statement statement = DB.makeStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS orders (name TEXT, price REAL, quantity INTEGER, PRIMARY KEY (name))");
            String sql2 = String.format("INSERT OR REPLACE INTO orders VALUES ('%s', %f, COALESCE((SELECT quantity FROM orders WHERE name='%s' AND price=%f), 0) + 1)", name, price, name, price);
            statement.executeQuery(sql2);
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}



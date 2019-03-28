package kiosk;
import java.sql.*;

class Order {
    static void addToOrder(Database DB, String name, float price) {
        try {
            String sqlCommand;

            Statement statement = DB.makeStatement();

            sqlCommand = "CREATE TABLE IF NOT EXISTS orders (name TEXT, price REAL, quantity INTEGER, PRIMARY KEY (name))";
            statement.execute(sqlCommand);

            sqlCommand = "INSERT OR REPLACE INTO orders VALUES ('%s', %f, COALESCE((SELECT quantity FROM orders WHERE name='%s' AND price=%f), 0) + 1)";
            statement.executeUpdate(String.format(sqlCommand, name, price, name, price));

        } catch (SQLException e) { e.printStackTrace(); }
    }
}
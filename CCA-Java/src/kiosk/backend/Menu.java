package kiosk.backend;

import kiosk.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The <code>Menu</code> class is a static class which contains
 * methods for querying the Menu Table in the database.
 */
public class Menu {
    /**
     * Gets a list of 'types' (Breakfast, Burgers, etc.) from the Menu Table in the database.
     * @return ArrayList&lt;String&gt; A list of all food 'types' in the Menu Table in the database.
     */
    public static ArrayList<String> generateTypes() {
        ArrayList<String> types = new ArrayList<>();
        try {
            Statement statement = Main.DB.makeStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT type FROM menu");

            while (rs.next()) { types.add(rs.getString("type")); }

            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return types;
    }

    /**
     * Gets a list of 'items' (Vanilla Cone etc.) from the Menu Table in the database, by the type
     * (Snacks and Treats etc.)
     * @param type The type of item (e.g., Breakfast, Burgers, Salads, etc.).
     * @return ArrayList&lt;String&gt; A list containing the names of all items in the Menu Table for the
     * specified <code>type</code>.
     */
    public static ArrayList<String> getItemsByType(String type) {
        ArrayList<String> items = new ArrayList<>();
        try {
            Statement statement = Main.DB.makeStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT name FROM menu WHERE type = '%s'", type));
            while (rs.next()) {
                items.add(rs.getString("name"));
            }
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return items;
    }

    /**
     * Gets the filepath of the image from the Menu Table in the database, for the food item named <code>name</code>.
     * @param name The name of the food or drink item for which the filepath is desired.
     * @return String The filepath of the image corresponding to the food item named <code>name</code>.
     */
    public static String getFilepath(String name) {
        String filepath = null;
        try {
            Statement statement = Main.DB.makeStatement();
            String sql = "SELECT filename FROM menu WHERE name = '%s'";
            ResultSet rs = statement.executeQuery(String.format(sql, name.replaceAll("'", "''")));
            filepath = rs.getString("filename");
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return filepath;
    }

    /**
     * Gets the price from the Menu Table in the database, for the food item named <code>name</code>.
     * @param name The name of the food or drink item for which the price is desired.
     * @return float The price of the food or drink item named <code>name</code>.
     */
    public static float getPrice(String name) {
        float price = 0;
        try {
            Statement statement = Main.DB.makeStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT price FROM menu WHERE name = '%s'", name));
            price = rs.getFloat("price");
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return price;
    }
}
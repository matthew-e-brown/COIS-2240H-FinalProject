package kiosk;
import java.sql.*;
import java.util.ArrayList;

class Menu {
    /* Gets a list of 'types' (Breakfast, Burgers, etc.) from the Menu Table in the database */
    static ArrayList<String> generateTypes(Database DB){
        ArrayList<String> types = new ArrayList<>();
        try {
            Statement statement = DB.makeStatement();
            ResultSet rs = statement.executeQuery("SELECT DISTINCT type FROM menu");

            while (rs.next()) { types.add(rs.getString("type")); }

            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return types;
    }

    /* Gets a list of 'items' (Vanilla Cone etc.) from the Menu table in the database, by the type (Snacks and Treats etc.) */
    static ArrayList<String> getItemsByType(Database DB, String type) {
        ArrayList<String> items = new ArrayList<>();
        try {
            Statement statement = DB.makeStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT name FROM menu WHERE type = '%s'", type));
            while (rs.next()) {
                items.add(rs.getString("name"));
            }
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return items;
    }

    /* Gets the filepath of the image from the Menu Table in the database, for the food item named 'name' */
    static String getFilepath(Database DB, String name) {
        String filepath = null;
        try {
            Statement statement = DB.makeStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT filepath FROM menu WHERE name = '%s'", name));
            filepath = rs.getString("filepath");
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return filepath;
    }

    /* Gets the price from the Menu Table in the database, for the food item named 'name' */
    static float getPrice(Database DB, String name) {
        float price = 0;
        try {
            Statement statement = DB.makeStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT price FROM menu WHERE name = '%s'", name));
            price = rs.getFloat("price");
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
        return price;
    }
}
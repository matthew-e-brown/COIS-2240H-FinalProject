package kiosk.backend;

import kiosk.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Menu {
    /* Gets a list of 'types' (Breakfast, Burgers, etc.) from the Menu Table in the database */
    public static ArrayList<String> generateTypes() {
        ArrayList<String> types = new ArrayList<>(); // create blank arraylist to be filled with the items from the database
        try {
            Statement statement = Main.DB.makeStatement(); // create statement using the referenced database connection (from database.java)
            ResultSet rs = statement.executeQuery("SELECT DISTINCT type FROM menu"); // call database using statement that looks for unique type entries in the menu database

            while (rs.next()) { types.add(rs.getString("type")); } // loop through the results and add the type row entries to the types arraylist initialized at the start of the function

            statement.close(); // close the statement used to retrieve the unique item types
        } catch (SQLException e) { e.printStackTrace(); } // if anything fails, error printStackTrace
        return types; // return the arraylist containing string values of the different types of food available on the menu
    }

    /* Gets a list of 'items' (Vanilla Cone etc.) from the Menu table in the database, by the type (Snacks and Treats etc.) */
    public static ArrayList<String> getItemsByType(String type) {
        ArrayList<String> items = new ArrayList<>(); // create blank arraylist to be filled with the items based on a given type from the database
        try {
            Statement statement = Main.DB.makeStatement(); // attempt to create a statement using the referenced database connection
            // call database using statement that looks for names in the menu database that are in rows with the type defined when calling the function
            ResultSet rs = statement.executeQuery(String.format("SELECT name FROM menu WHERE type = '%s'", type));
            while (rs.next()) {
                items.add(rs.getString("name")); // loop through the results and add the name row entries to the names arraylist initialized at the start of the function
            }
            statement.close(); // close the statement used to retrieve the names of the items
        } catch (SQLException e) { e.printStackTrace(); } // if anything fails, printStackTrace
        return items; // return the arraylist containing string values of the different item names based on the given type
    }

    /* Gets the filepath of the image from the Menu Table in the database, for the food item named 'name' */
    public static String getFilepath(String name) {
        String filepath = null; //  initialized filepath as a null value incase no filepath is found
        try {
            Statement statement = Main.DB.makeStatement(); // attempt to create a statement using the referenced database connection
            String sql = "SELECT filename FROM menu WHERE name = '%s'"; // create a string to hold the statement to retrieve the filename from the menu database for the defined item name
            ResultSet rs = statement.executeQuery(String.format(sql, name.replaceAll("'", "''"))); // format the previously created string to contain the name of the item.
            filepath = rs.getString("filename"); // retrieve the string row value from the results
            statement.close(); // close the statement used to retrieve the string value of the filepath
        } catch (SQLException e) { e.printStackTrace(); } // if anything fails, error printStackTrace
        return filepath; // return the string filepath that was retrieved from the database
    }

    /* Gets the price from the Menu Table in the database, for the food item named 'name' */
    public static float getPrice(String name) {
        float price = 0; //  initialized the price to a default $0.00
        try {
            Statement statement = Main.DB.makeStatement(); // attempt to create a statement using the referenced database connection
            // call database using statement that looks for the price in database menu where the name is the defined name (in the argument)
            String sql = "SELECT price FROM menu WHERE name = '%s'";
            ResultSet rs = statement.executeQuery(String.format(sql, name.replaceAll("'", "''")));
            price = rs.getFloat("price"); // set the float price as the price row value from the results
            statement.close(); // close the statement used to retrieve the float value of the price
        } catch (SQLException e) { e.printStackTrace(); } // if anything fails, error printStackTrace
        return price; // return the float price that was retrieved from the menu database
    }
}

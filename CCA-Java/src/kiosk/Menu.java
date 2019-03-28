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
        } catch (SQLException e) { System.out.println("Something went wrong: " + e.getMessage()); }
        return types;
    }

    static ArrayList<ArrayList<String>> rowsByType(Database DB, String type){
        ArrayList<ArrayList<String>> rows = new ArrayList<>(3);
        try {
            Statement statement = DB.makeStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM menu WHERE type='%s'", type));
            while (rs.next()) {
                rows.get(0).add(rs.getString("filename"));
                rows.get(1).add(rs.getString("name"));
                rows.get(2).add(((Float)rs.getFloat("price")).toString());
            }
        } catch (SQLException e) { System.out.println("Something went wrong: " + e.getMessage()); }
        return rows;
    }

}
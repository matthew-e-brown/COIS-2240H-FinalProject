package kiosk;
import java.sql.*;
import java.util.ArrayList;

public class Menu{
    public static ArrayList<String> generateTypes(Database DB){
        ArrayList<String> types = new ArrayList<>();
        try {
            Statement stmt = DB.makeStatement();
            String sql = "SELECT DISTINCT type FROM menu";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                types.add(rs.getString("type"));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return types;
    }

    public static ArrayList<ArrayList<String>> rowsByType(Database DB, String type){
        ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>(3);
        try {
            Statement stmt = DB.makeStatement();
            String sql = String.format("SELECT * FROM menu WHERE type='%s'", type);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rows.get(1).add(rs.getString("filename"));
                rows.get(2).add(rs.getString("name"));
                rows.get(3).add(((Float)rs.getFloat("price")).toString());
            }
        }catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return rows;
    }

}
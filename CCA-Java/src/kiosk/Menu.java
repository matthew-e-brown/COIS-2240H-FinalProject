public class Menu{
    public static ResultSet generateTypes(Database DB){
        ResultSet rs;
        Statement stmt;
        try {
            stmt = DB.createStatement();
            String sql = "SELECT DISTINCT type FROM menu";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                types.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        stmt.close();
        return rs;
    }

    public static ResultSet rowsByType(Database DB, String type){
        ResultSet rs;
        try {
            Statement stmt = DB.createStatement();
            String sql = String.format("SELECT * FROM menu WHERE type='%s'", type);
            rs = stmt.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return rs;
    }

}
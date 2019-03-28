public class Database{
    String filepath;
    Connection conn;

    public Database(String filepath) {
        this.filepath = filepath;
        connectToDB();
    }

    public void connectToDB(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", filepath);
        }
        catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        this.conn = conn;
    }

    public static Statement createStatement() {
        Statement statement = null;
        try {
            statement = this.conn.createStatement();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return statement;
    }


    public static void closeConnection() {
        try {
            this.conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

}
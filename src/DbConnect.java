import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnect {

    private static DbConnect instance;
    private Connection conn;
    private String db = "jdbc:mysql://52.50.23.197:3306/world";
    private String username = "cctstudent";
    private String password = "Pass1234!";

    private DbConnect() {


        try {

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(db, username, password);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery("SELECT * FROM country");

            // Loop through the result set
            while (rs.next())
            System.out.println(rs.getString("Code"));
            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static DbConnect getInstance() {
        if (instance == null) {
            instance = new DbConnect();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

}

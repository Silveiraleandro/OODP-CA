import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    //this class recieves the data and delivers it to the next next class (countryDAO or database)
    // it is a bridge between the database class and the countryDAO
public class DbConnect {

    private static DbConnect instance;
    private String db = "jdbc:mysql://52.50.23.197:3306/world";
    private String username = "cctstudent";
    private String password = "Pass1234!";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs = null;

    //my constructor method establishes the db connection
    private DbConnect() {


        try {

            // Get a connection to the database
            DriverManager.getConnection(db, username, password);

            // Get a statement from the connection
            conn.createStatement();

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
    //executes and stores the new data to the db
    public boolean storeNew(String query) {
        // Execute the query
        try {
            stmt.execute(query);
            return true;

        } catch (SQLException e) {
            //catch block
            e.printStackTrace();
            return false;
        }
    }

    //method that selects any query that the client needs
    public ResultSet select(String query) {
        // Execute the query
        try {
            rs = stmt.executeQuery(query);


        } catch (SQLException e) {
            //catch block
            e.printStackTrace();
        }
        return rs;
    }

    // close method closes the result,  statement and the connection
    public void close() {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

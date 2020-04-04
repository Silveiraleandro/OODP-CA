/*Code structure provided by our Lecturer Amilcar Aponte
 *Code adapted and modified by @Author = Leandro Silveira
 *Code can be checked on: https://moodle.cct.ie/course/view.php?id=1505 Singleton file 17th feb - 23th feb
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
this class converses with the external database
 */

public class DbConnect {

    //instance to provide access in other classes e categorizes Singleton pattern
    private static DbConnect instance;

    //private Global variables
    private String db = "jdbc:mysql://52.50.23.197:3306/world";
    private String username = "cctstudent";
    private String password = "Pass1234!";
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;

    /*
    my constructor method establishes the db connection and categorizes Singleton pattern
     */
    private DbConnect() {


        try {
            /*
            Get a connection to the database
             */
            Connection conn = DriverManager.getConnection(db, username, password);
            this.conn = conn;

        } catch (SQLException exp) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (exp != null) {
                System.out.println("State  : " + exp.getSQLState());
                System.out.println("Message: " + exp.getMessage());
                System.out.println("Error  : " + exp.getErrorCode());

                exp = exp.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        instance = this;

    }

    /*
    This method provides access to the instance of the DbConnect singleton class
     */
    public static DbConnect getInstance() {
        if (instance == null) {
            instance = new DbConnect();
        }
        return instance;
    }

    /*
    executes and stores the new data to the db
     */
    public boolean storeNew(String query) {
        /*
         creating and saving the statement from the database
         Execute the query
         */
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            return true;

        } catch (SQLException e) {
            //catch block
            System.out.println("Duplicated values");
            return false;
        }
    }


    /*
        method that selects any query that the client needs
         */
    public ResultSet select(String query) {
        // Execute the query
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            //catch block
            e.printStackTrace();
        }
        return rs;
    }

    /*
     close method closes the result,  statement and the connection
     */
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

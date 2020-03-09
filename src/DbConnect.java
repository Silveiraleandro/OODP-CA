import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//this is the database class
public class DbConnect {

        static private DbConnect instance;
        static private Connection con;
        static private Statement stmt;
        static private ResultSet rs;
//i am using a private constructor due to the need to apply Singleton approach
        public DbConnect() {
            getConnection();

        }
        //this method checks if there is not already a connection running an returns a connection instance if so
        public static DbConnect getInstance() throws SQLException {
            if (instance == null) {
                instance = new DbConnect();
            }
            return instance;
        }
        //this method establishes the connection with the data base (I have not finished yet.. to be honest im kinda lost
        public Connection getConnection(){
            if(con==null){
                try {
                    String host = "jdbc:mysql://52.50.23.197:3306/world";
                    String username = "cctstudent";
                    String password = "Pass1234!";
                    String query = "SELECT * FROM country";
                    con = DriverManager.getConnection(host, username, password);

                /*    while (rs.next()){
                        System.out.println(rs.getString("Code")) + "\t" + rs.getString("Name")+ "\t" + rs.getString("Continent")+ "\t" + rs.getString("SurfaceArea") + "\t" + rs.getString("HeadOfState"));
                    }  */

                } catch(SQLException ex) {
                    Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null,ex);
                }

                }
            return con;
            
        }
}

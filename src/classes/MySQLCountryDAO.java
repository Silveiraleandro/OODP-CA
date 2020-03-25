package classes;

import interfaces.CountryDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {
    //Creating instances of countries and saving them in an Array list of countries
    // and returning the result of the array
    @Override
    public ArrayList<Country> getCountries() {


        ArrayList<Country>countries = new ArrayList<Country>();

        String query = "SELECT * FROM country";
        ResultSet rs = DbConnect.getInstance().select(query);
        int code = 0;
        String name = "";
        String continent = "";
        float surfaceArea = 0;
        String headOfState = "";
        Country c = null;

        try {
            while (rs.next()){
            code = rs.getInt(1);
            name = rs.getString(2);
            continent = rs.getString(3);
            surfaceArea = rs.getFloat(4);
            headOfState = rs.getString(5);

            c = new Country(code, name, continent, surfaceArea, headOfState);
            countries.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return countries;
    }
    //This method receives a code from the outside and calls the db with the given query
    //populates the variables with the result set, creates a instance of country and returns it
    @Override
    public Country findCountryByCode(int code) {

        String query = "SELECT * FROM country WHERE code = " + code + ";";
        ResultSet rs = DbConnect.getInstance().select(query);
        String name = "";
        String continent = "";
        float surfaceArea = 0;
        String headOfState = "";
        Country c = null;

        try {
            if (rs.next()){
                name = rs.getString(2);
                continent = rs.getString(3);
                surfaceArea = rs.getFloat(4);
                headOfState = rs.getString(5);

                c = new Country(code, name, continent, surfaceArea, headOfState);

                return c;
            }

            return null;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //This method receives a name from the outside and calls the db with the given query
    //populates the variables with the result set, creates a instance of country and returns it
    @Override
    public Country findCountryByName(String name) {


        String query = "SELECT * FROM country WHERE name = " + name + ";";
        ResultSet rs = DbConnect.getInstance().select(query);
        int code = 0;
        String continent = "";
        float surfaceArea = 0;
        String headOfState = "";
        Country c = null;

        try {
            if (rs.next()){
                code = rs.getInt(1);
                continent = rs.getString(3);
                surfaceArea = rs.getFloat(4);
                headOfState = rs.getString(5);

                c = new Country(code, name, continent, surfaceArea, headOfState);

                return c;
            }

            return null;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveCountry(Country country) {

        int code = country.getCode();
        String name = country.getName();
        String continent = country.getContinent();
        float surfaceArea = country.getSurfaceArea();
        String headOfState = country.getHeadOfState();

        String query = "INSERT INTO world.country(Code, Name, Continent, SurfaceArea, HeadOfState)values("+code+", '"+name+"', '"+continent+"', "+surfaceArea+", '"+headOfState+"');";
        return DbConnect.getInstance().storeNew(query);
    }
}

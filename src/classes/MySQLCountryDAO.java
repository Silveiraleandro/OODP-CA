package classes;

import interfaces.CountryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {

    String code = "";
    String name = "";
    Continent continent;
    float surfaceArea = 0;
    String headOfState = "";


    //Creating instances of countries and saving them in an Array list of countries
    // and returning the result of the array
    @Override
    public ArrayList<Country> getCountries() {


        ArrayList<Country> countries = new ArrayList<Country>();
        Country c = null;
        String query = "SELECT * FROM country";
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            while (rs.next()) {

                code = rs.getString(1);
                name = rs.getString(2);
                String continentName = rs.getString(3).replace(" ","");
                if(continentName.isEmpty()) {
                    continue;
                }
                continent = Continent.valueOf(rs.getString(3).replace(" ", ""));
                surfaceArea = rs.getFloat(4);
                headOfState = rs.getString(5);

                c = new Country.BuilderCountry(code, name, continent, surfaceArea, headOfState).build();
                countries.add(c);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;

    }

    //This method receives a code from the outside and calls the db with the given query
    //populates the variables with the result set, creates a instance of country and returns it
    @Override
    public Country findCountryByCode(String code) {

        Country thisCountry = null;
        String query = "SELECT * FROM country WHERE code = " + code + ";";
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            if (rs.next()) {

                code = rs.getString(1);
                name = rs.getString(2);
                String continentName = rs.getString(3).replace(" ", "");
                if (continentName.isEmpty()) {
                    return null;
                }
                continent = Continent.valueOf(rs.getString(3).replace(" ", ""));
                surfaceArea = rs.getFloat(4);
                headOfState = rs.getString(5);

                thisCountry = new Country.BuilderCountry(code, name, continent, surfaceArea, headOfState).build();

            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thisCountry;

    }

    //This method receives a name from the outside and calls the db with the given query
    //populates the variables with the result set, creates a instance of country and returns it
    @Override
    public Country findCountryByName(String name) {

        Country thisCountry = null;
        String query = "SELECT * FROM country WHERE Name = '" + name + "';" ;
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            if (rs.next()) {

                code = rs.getString(1);
                name = rs.getString(2);
                String continentName = rs.getString(3).replace(" ", "");
                if (continentName.isEmpty()) {
                    return null;
                }
                continent = Continent.valueOf(rs.getString(3).replace(" ", ""));
                surfaceArea = rs.getFloat(4);
                headOfState = rs.getString(5);

                thisCountry = new Country.BuilderCountry(code, name, continent, surfaceArea, headOfState).build();

            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thisCountry;

    }

    @Override
    public boolean saveCountry(Country country) {

        String code = country.getCode();
        String name = country.getName();
        Continent continent = country.getContinent();
        float surfaceArea = country.getSurfaceArea();
        String headOfState = country.getHeadOfState();

        String query = "INSERT INTO world.country(Code, Name, Continent, SurfaceArea, HeadOfState)values(" + code + ", '" + name + "', '" + continent + "', " + surfaceArea + ", '" + headOfState + "');";
        return DbConnect.getInstance().storeNew(query);
    }
}

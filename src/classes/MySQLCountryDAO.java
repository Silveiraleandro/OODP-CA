package classes;

import interfaces.CountryDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {

    Country country;
    //Creating instances of countries and saving them in an Array list of countries
    // and returning the result of the array
    @Override
    public ArrayList<Country> getCountries() {

        ArrayList<Country> countries = new ArrayList<Country>();
        String query = "SELECT * FROM country";
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            while (rs.next()) {

              String  code = rs.getString(1);
              String name = rs.getString(2);
              String continentName = rs.getString(3).replace(" ", "");
                if (continentName.isEmpty()) {
                    continue;
                }
              Continent  continent = Continent.valueOf(rs.getString(3).replace(" ", ""));
              float  surfaceArea = rs.getFloat(4);
              String headOfState = rs.getString(5);

                Country country = new Country.BuilderCountry(code, name, continent, surfaceArea, headOfState).build();
                countries.add(country);
                //Close connection
                DbConnect.getInstance().close();
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

        String query = "SELECT * FROM country WHERE code = '" + code + "';";
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            if (rs.next()) {

            String coder = rs.getString(1);
            String name = rs.getString(2);

              Continent  continent = Continent.valueOf(rs.getString(3).replace(" ", ""));
               float surfaceArea = rs.getFloat(4);
               String headOfState = rs.getString(5);

                country = new Country.BuilderCountry(coder, name, continent, surfaceArea, headOfState).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;

    }

    //This method receives a name from the outside and calls the db with the given query
    //populates the variables with the result set, creates a instance of country and returns it
    @Override
    public ArrayList<Country> findCountryByName(String name) {

        ArrayList<Country> requestedCountries = new ArrayList<>();
        //name LIKE '%\"+name+\"%'+name.trim()+;";
        String query = "SELECT * FROM country WHERE Name = '" + name + "';";
        System.out.println(query);
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            while (rs.next()) {

                String code = rs.getString(1);
                String nameR = rs.getString(2);
                String continentName = rs.getString(3).replace(" ", "");
                if (continentName.isEmpty()) {
                    continue;
                }
                Continent continent = Continent.valueOf(continentName);
                float surfaceArea = rs.getFloat(4);
                String headOfState = rs.getString(5);

                Country country = new Country.BuilderCountry(code, nameR, continent, surfaceArea, headOfState).build();
                requestedCountries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return requestedCountries;

    }

    //This method receives a country coming from the user class and saves this new entrance into the database
    @Override
    public boolean saveCountry(Country country) {
        String code = country.getCode();
        String name = country.getName();
        Continent continent = country.getContinent();
        float surfaceArea = country.getSurfaceArea();
        String headOfState = country.getHeadOfState();

      // String query = "INSERT INTO world.country(Code, Name, Continent, SurfaceArea, HeadOfState)values('" + code + "', '" + name + "', '" + continent.getContinent() + "', " + surfaceArea + ", '" + headOfState + "');";
        String query = "INSERT INTO world.country(Code, Name, Continent, SurfaceArea, HeadOfState)values('" + code + "', '" + name + "', '" + continent + "', '" + surfaceArea + "', '" + headOfState + "');";
        return DbConnect.getInstance().storeNew(query);


    }

}

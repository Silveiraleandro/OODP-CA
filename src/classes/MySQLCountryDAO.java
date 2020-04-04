/*Code structure provided by our Lecturer Amilcar Aponte
 *Code adapted and modified by @Author = Leandro Silveira
 *Code can be checked on: https://moodle.cct.ie/course/view.php?id=1505 file DAO pattern 16th Mar 22nd Mar
 * or viewed on videos 1 https://www.youtube.com/watch?v=xl66O_VVKGM  2 https://www.youtube.com/watch?v=X3G5C6wdeqw
 * 3 https://www.youtube.com/watch?v=vR-s6YkSxI8 and 4 https://www.youtube.com/watch?v=geY4LYT4yZ4
 */
package classes;

/*
This class in the implementation of the Country DAO
It is going to communicate with the user class and DbConnect class
allowing them to request and deliver info to each other
*/

import interfaces.CountryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {

    Country country;

    /*
    Creating instances of countries and saving them in an Array list of countries
    and returning the result of the array
      */
    @Override
    public ArrayList<Country> getCountries() {

        ArrayList<Country> countries = new ArrayList<>();
        String query = "SELECT * FROM country";
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            while (rs.next()) {

                String code = rs.getString(1);
                String name = rs.getString(2);
                String continentName = rs.getString(3).replace(" ", "");
                if (continentName.isEmpty()) {
                    continue;
                }
                Continent continent = Continent.valueOf(rs.getString(3).replace(" ", ""));
                float surfaceArea = rs.getFloat(4);
                String headOfState = rs.getString(5);

                Country country = new Country.BuilderCountry(code, name, continent, surfaceArea, headOfState).build();
                countries.add(country);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;

    }

    /*
    This method receives a code from the outside and calls the db with the given query
    populates the variables with the result set, creates a instance of country and returns it
    */
    @Override
    public Country findCountryByCode(String code) {

        String query = "SELECT * FROM country WHERE code = '" + code + "';";
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            if (rs.next()) {

                String name = rs.getString(2);
                String continentName = rs.getString(3).replace(" ", "");
                if (continentName.isEmpty()) {
                    return null;
                }
                Continent continent = Continent.valueOf(continentName);
                float surfaceArea = rs.getFloat(4);
                String headOfState = rs.getString(5);

                country = new Country.BuilderCountry(code, name, continent, surfaceArea, headOfState).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;

    }

    /*
    This method receives a name from the outside and calls the db with the given query
    populates the variables with the result set, creates a instance of country and returns it
     */
    @Override
    public ArrayList<Country> findCountryByName(String name) {

        ArrayList<Country> requestedCountries = new ArrayList<>();
        String query = "SELECT * FROM country WHERE Name LIKE '%" + name.trim() + "%';";
        ResultSet rs = DbConnect.getInstance().select(query);

        try {
            while (rs.next()) {

                String code = rs.getString(1);
                String continentName = rs.getString(3).replace(" ", "");
                if (continentName.isEmpty()) {
                    continue;
                }
                Continent continent = Continent.valueOf(continentName);
                float surfaceArea = rs.getFloat(4);
                String headOfState = rs.getString(5);

                Country country = new Country.BuilderCountry(code, name, continent, surfaceArea, headOfState).build();
                requestedCountries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return requestedCountries;

    }

    /*
    This method receives a country coming from the user class and saves this new entrance into the database
    prints a message If the country is successfully saved or if something went wrong
     */
    @Override
    public boolean saveCountry(Country country) {
        String code = country.getCode();
        String name = country.getName();
        Continent continent = country.getContinent();
        float surfaceArea = country.getSurfaceArea();
        String headOfState = country.getHeadOfState();

        String query = "INSERT INTO world.country(Code, Name, Continent, SurfaceArea, HeadOfState)values('" + code + "', '" + name + "', '" + continent.getContinent() + "', " + surfaceArea + ", '" + headOfState + "');";

        boolean store = DbConnect.getInstance().storeNew(query);
        if (store) {
            System.out.println("The country is now in the system :)\n");
        } else {
            System.out.println("Something went wrong, Please try again :(\n");
        }
        return store;
    }


}

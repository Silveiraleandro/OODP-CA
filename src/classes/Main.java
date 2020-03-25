package classes;

import interfaces.CountryDAO;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        CountryDAO dao = new MySQLCountryDAO();

        ArrayList<Country>countries = dao.getCountries();
        System.out.println(countries);

        Country c = dao.findCountryByCode(49);
        System.out.println(c);

        dao.saveCountry(c);
        System.out.println(c);
    }
}

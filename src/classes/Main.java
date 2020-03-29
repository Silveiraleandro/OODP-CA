package classes;

import interfaces.CountryDAO;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {

        CountryDAO dao = new MySQLCountryDAO();
        ArrayList<Country>countries = dao.getCountries();

        for(Country c : countries){
            System.out.println(c);
        }

    /*   CountryDAO dao = new MySQLCountryDAO();

        ArrayList<Country>countries = dao.getCountries();
        System.out.println(countries);

        Country c = dao.findCountryByCode(49);
        System.out.println(c);

        dao.saveCountry(c);
        System.out.println(c);

        Country.BuilderCountry builder = new Country.BuilderCountry(00000, "safadao", Continent.valueOf("Africa") ,23300, "beto jamaica");
        Country c = builder.build();
        System.out.println(c);*/
    }
}

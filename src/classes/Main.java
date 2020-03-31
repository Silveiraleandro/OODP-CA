package classes;

import interfaces.CountryDAO;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) {

        User userMenu = new User();
        userMenu.menu();

     /*   CountryDAO dao = new MySQLCountryDAO();
        ArrayList<Country>countries = dao.getCountries();
        System.out.println(countries);
       //all countries
        ArrayList<Country>countries = dao.getCountries();
        for(Country c : countries){
            System.out.println();

        }
        //by code
        Country test = dao.findCountryByCode("143");
        System.out.println(test);

        //by name
        Country test1 = dao.findCountryByName("Brasil");
        System.out.println(test1);

        //saveCountry
        Country.BuilderCountry builder = new Country.BuilderCountry("pp", "boteco", Continent.Antarctica, 2009, "Juliao");
        Country x = builder.build();
        System.out.println(x);

        MySQLCountryDAO countryToSave = new MySQLCountryDAO(x);                 */

        /*c.addCountry();
        MySQLCountryDAO newCountry = new MySQLCountryDAO();    */




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

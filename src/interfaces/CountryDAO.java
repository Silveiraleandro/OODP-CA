package interfaces;
import classes.Country;
import java.util.ArrayList;

//this interface contains the methods that the classes implementing DAO must have
public interface CountryDAO {
    public ArrayList<Country> getCountries();

    public Country findCountryByCode(String code);

    public ArrayList<Country>findCountryByName(String name);

    public boolean saveCountry(Country country);
}

package interfaces;
import java.util.ArrayList;


public interface CountryDAO {
    public ArrayList<Country> getCountry();
    public Country findCountryByCode(int code);
    public Country findCountryByName(String name);
    public boolean saveCountry(Country country);
}

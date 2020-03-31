package classes;

import interfaces.CountryDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//this class shows a menu and connects with the class Main
public class User {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public CountryDAO daobj = new MySQLCountryDAO();
    Country country;

    public User() {

        menu();
    }

    //menu method displays the menu
    public void menu() {
        System.out.println("trying to find a Country?");
        System.out.println("---------------------------------");
        System.out.println("Menu Options:");
        System.out.println("1. View of all Countries");
        System.out.println("2. Find a Country by it's code");
        System.out.println("3. Find a Country by it's name");
        System.out.println("4. Add a new Country to the list");
        System.out.println("5. Exit the program");
        System.out.println("----------------------------------");
        System.out.print("Please select an option from 1-5\r\n");

        try {
            int input = Integer.parseInt(reader.readLine());

            if (input < 0 || input > 5) {
                System.out.println("You have entered an invalid selection, please try again\r\n");
            } else if (input == 5) {
                System.out.println("You have quit the program\r\n");
                System.exit(1);
            } else {
                System.out.println("You have entered " + input + "\r\n");
            }
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your input!\r\n");
            System.exit(1);
        }
    }

    public void userMenu(Integer option) {
        MySQLCountryDAO dao = new MySQLCountryDAO();
        switch (option) {
            case 1:
                viewAllCountries();
                break;
            case 2:
                searchCountryByCode();
                break;
            case 3:

                break;
            case 4:

                break;
            default:
                System.out.println("Have a nice day!");
                System.exit(0);
        }
        menu();
    }

    private void viewAllCountries() {
        System.out.println("view all Countries!\n");
        ArrayList<Country> countries = daobj.getCountries();
        System.out.println(countries);
        menu();
    }

    private void searchCountryByCode() {

        System.out.println("Find a Country by Country Code!");
        System.out.println("-------------------------------");
        System.out.println(" Type in the 3 digits code: \n ");

        String input = null;

        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (input.length() <= 3) {
            country = daobj.findCountryByCode(input);
            System.out.println("Result of your search:");
            System.out.println(country);
            menu();
        } else {
            System.out.println("Please Try a valid 3 digit valid code.\n");
            menu();
        }

        private void searchCountryByName(){

            ArrayList<Country> allCountries = daobj.findCountryByName(input);
            System.out.println("Find a Country by Country Name!");
            System.out.println("Type in the Country Name: \n");

            input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Result of your search:");
            System.out.println(allCountries);
            menu();
        }
    }
}


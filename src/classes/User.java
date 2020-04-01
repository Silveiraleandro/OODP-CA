package classes;

import interfaces.CountryDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//this class shows a menu and connects with the class Main
public class User {

    CountryDAO daobj = new MySQLCountryDAO();
    Country country;


    public User() {

        userMenu();
    }

    //method to read input from the user
    private String userReader() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    //method that displays the main menu that the user sees
    public void userMenu() {

        System.out.println("-----------------------------------");
        System.out.println("Menu Options to:");
        System.out.println("1. View of all Countries");
        System.out.println("2. Find a Country by it's code");
        System.out.println("3. Find a Country by it's name");
        System.out.println("4. Create a Country  and to the list");
        System.out.println("5. Exit the program");
        System.out.println("----------------------------------");
        System.out.print("Please select an option from 1-5\r\n");

        String input = userReader();
        int in = Integer.parseInt(input);

        if (input.length() < 0 || input.length() > 5) {
            System.out.println("You have entered an invalid selection, please try again\r\n");
            internalMenu(in);
        } else if (input.equals(5)) {
            System.out.println("You have quit the program\r\n");
            System.exit(1);
        } else {
            System.out.println("You have chosen to: ");
            internalMenu(in);
        }
    }
    //this method displays the internalMenu
    public void internalMenu(Integer option) {
        MySQLCountryDAO dao = new MySQLCountryDAO();
        switch (option) {
            case 1:
                viewAllCountries();
                break;
            case 2:
                searchCountryByCode();
                break;
            case 3:
                searchCountryByName();
                break;
            case 4:
                createCountry();
                break;
            default:
                System.out.println("See you again soon!");
                System.exit(0);
        }
        userMenu();
    }
    //method that displays all the countries in the db
    private void viewAllCountries() {
        System.out.println("view all Countries!\n");

        ArrayList<Country> allCountries = daobj.getCountries();
        System.out.println(allCountries);
        userMenu();
    }
    //method that searches for a country in the db, which code matches a code given by a user and prints the country in the terminal
    private void searchCountryByCode() {

        System.out.println("Find a Country by it's Code!");
        System.out.println(" Type in the Country code: \n ");

        String input = userReader();

        if (input.length() <= 3) {
            country = daobj.findCountryByCode(input);
            System.out.println("The result search for the code " + input + " is:");
            System.out.println(country);
            userMenu();
        } else {

            System.out.println("Sorry but " + input + " is not a valid code. Please enter a code with max 3 digits.\n");
            searchCountryByCode();
        }
    }
    //method that searches for one or more countries in the db, which name matches a name given by a user and prints the country(s) in the terminal
    private void searchCountryByName() {
        System.out.println("Find a Country by it's Name!");
        System.out.println("Type in the Country Name: \n");

        ArrayList<Country> countries = null;
        String input = userReader();
        countries = daobj.findCountryByName(input);

        if (countries.size() <= 0) {

            System.out.println("Sorry but we could not find " + input + " in our list");
        } else {

            System.out.println("The result search for the name " + input + " is:");
            System.out.println(countries);
        }
        userMenu();
    }
    //method creates and save a new country in the db
    private void createCountry() {
        //display in the screen
        System.out.println("Create and save a new Country!");
        System.out.println("Please start inserting a 1 to 3 digits Country code:  \n");

        //code insertion validation
        String input = userReader();
        if (input.length() > 3) {
            System.out.println("Please try again typing in a 1, 2 or 3 digits code");
            createCountry();
        }else if (daobj.findCountryByCode(input).equals(" ")) {
            System.out.println("The code "+input+" already exists in oustem. Please insert a different code");
        }
        System.out.println("Please insert the country name: ");

    }

}



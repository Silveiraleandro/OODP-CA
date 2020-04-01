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

        System.out.println("-----------------------------------");
        System.out.println("Menu Options to:");
        System.out.println("1. View of all Countries");
        System.out.println("2. Find a Country by it's code");
        System.out.println("3. Find a Country by it's name");
        System.out.println("4. Create a Country  and to the list");
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
                System.out.println("You have chosen to: ");
                userMenu(input);
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
                searchCountryByName();
                break;
            case 4:
                createCountry();
                break;
            default:
                System.out.println("Have a nice day!");
                System.exit(0);
        }
        menu();
    }

    private void viewAllCountries() {
        System.out.println("view all Countries!\n");

        ArrayList<Country> allCountries = daobj.getCountries();
        System.out.println(allCountries);
        menu();
    }

    private void searchCountryByCode() {

        System.out.println("Find a Country by it's Code!");
        System.out.println(" Type in the Country code: \n ");

        String input = null;

        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (input.length() <= 3) {
            country = daobj.findCountryByCode(input);
            System.out.println("Result of your search is:");
            System.out.println(country);
            menu();
        } else {
            System.out.println("Please Try a valid code with max 3 digits.\n");
            menu();
        }
    }

    private void searchCountryByName() {

        System.out.println("Find a Country by it's Name!");
        System.out.println("Type in the Country Name: \n");

        String input;
        ArrayList<Country> allCountries = null;

        try {
            input = reader.readLine();
            country = daobj.findCountryByName(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        allCountries.add(country);
        System.out.println("Result of your search:");
        System.out.println(allCountries);
        menu();
    }

    private void createCountry() {

        String input;

        System.out.println("Create and save a new Country!");
        System.out.println("Please start inserting a 1 to 3 digits Country code  \n");



    }

}



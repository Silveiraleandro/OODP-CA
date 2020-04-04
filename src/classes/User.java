/*
@Author: Leandro Silveira
 */


package classes;

import interfaces.CountryDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
this class shows a menu and connects with the class Main
 */
public class User {

    CountryDAO daobj = new MySQLCountryDAO();
    Country country;


    public User() {

        userMenu();
    }

    /*
    method to read input from the user
     */
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

    /*
    method that displays the main menu that the user sees
     */
    public void userMenu() {


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>WELCOME USER>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Menu Options to:");
        System.out.println("1. View of all Countries");
        System.out.println("2. Find a Country by it's code");
        System.out.println("3. Find a Country by it's name");
        System.out.println("4. Create a Country  and to the list");
        System.out.println("5. Exit the program");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.print("Please select an option from 1-5\r\n");

        String input = userReader();

        if (!input.matches("[1-5]")) {
            System.out.println("Select a number from 1-5 please try again\r\n");
            userMenu();
        } else {
            int in = Integer.parseInt(input);
            System.out.println("You have chosen to: ");
            internalMenu(in);
        }

    }

    /*
    this method displays the internalMenu
     */
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
            case 5:
                System.out.println("Thank you for using the system");
                System.exit(0);
                //closing the connection with the database
                DbConnect.getInstance().close();
                break;
        }
        userMenu();
    }

    /*
    method that displays all the countries in the db
     */
    private void viewAllCountries() {
        System.out.println("view all Countries!\n");

        ArrayList<Country> allCountries = daobj.getCountries();
        System.out.println(allCountries);
        userMenu();
    }

    /*
    method that searches for a country in the db, which code matches a code given by a user and prints the country in the terminal
     */
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

    /*
    method that searches for one or more countries in the db, which name matches a name given by a user and prints the country(s) in the terminal
     */
    private void searchCountryByName() {
        System.out.println("Find a Country by it's Name!");
        System.out.println("Type in the Country Name: \n");

        ArrayList<Country> countries = null;
        String input = userReader();
        countries = daobj.findCountryByName(input);

        if (countries.size() <= 0) {

            System.out.println("Sorry but we could not find " + input + " in our list");
            searchCountryByName();
        } else {

            System.out.println("The result search for the name " + input + " is:");
            System.out.println(countries);
        }
        userMenu();
    }

    /*
    method creates and save a new country in the db
     */
    private void createCountry() {

        System.out.println("Create and save a new Country!");
        System.out.println("Please start inserting a 1 to 3 digits Country code:  \n");
        String inputCode;
        //code insertion validation
        inputCode = validatingInputCode();

        System.out.println("Please insert the country name: ");
        String inputName = userReader();

        System.out.println("Please pick one of 7 continents to place the country in ? [1-7]: " + "\n1 - Europe\n2 - Asia\n3 - North America\n4 - South America\n5 - Africa\n6 - Oceania\n7 - Antarctica");
        System.out.println("Please enter only one number from 1 to 7.");

        //saving in the variable input the user input that is coming with the method
        Continent continent = continentChoice();

        System.out.println("Please insert the land area of the country");
        String userInput = userReader();
        if (!userInput.matches("[1-9]" + "[.]") || userInput.length() > 100000) {
            System.out.println("Please insert only numbers for land area");
            createCountry();
        }
        float inputSurfaceArea = Float.parseFloat(userInput);

        System.out.println("Please insert the name of the President/king");
        String inputHeadOfState = userReader();

        /*
        Create a new object country with all the attributes
         */
        country = new Country.BuilderCountry(inputCode, inputName, continent, inputSurfaceArea, inputHeadOfState).build();

        //saving the created new country
        daobj.saveCountry(country);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>THANK YOU>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("   thank you for using our system    ");
        userMenu();
    }

    /*
    this method facilitates when choosing the continent.
    It is basically a Switch is a control statement that allows a value to change control of execution.
     */
    public Continent continentChoice() {

        Continent inputContinent = null;

        String option = userReader();
        while (!option.matches("[1-7]")) {

        }
        switch (Integer.parseInt(option)) {
            case 1:
                inputContinent = Continent.Europe;
                break;
            case 2:
                inputContinent = Continent.Asia;
                break;
            case 3:
                inputContinent = Continent.NorthAmerica;
                break;
            case 4:
                inputContinent = Continent.SouthAmerica;
                break;
            case 5:
                inputContinent = Continent.Africa;
                break;
            case 6:
                inputContinent = Continent.Oceania;
                break;
            case 7:
                inputContinent = Continent.Antarctica;
                break;
        }

        return inputContinent;
    }

    /*
    this method verifies if the code the customer inputs already exists in the db
    and if it is within the allowed length
    */
    private String validatingInputCode() {

        String inputCode = "";
        inputCode = userReader();

        if (inputCode.length() > 3) {
            System.out.println("Remember it must be 1 to 3 digits code. try again");

            country = daobj.findCountryByCode(inputCode);

            validatingInputCode();
            //if country  has any entry (rs to validade)
        }
        if (country == null) {

            System.out.println("Sorry, this code already exists. try again");
            validatingInputCode();
        } else {
            System.out.println("The code entered was: " + inputCode);

        }

        return inputCode;
    }
}



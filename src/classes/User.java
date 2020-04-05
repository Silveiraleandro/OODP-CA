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

        System.out.println("Hello User........Menu Options to: \n");
        System.out.println("1. list of all Countries in the db");
        System.out.println("2. Find a Country by it's code");
        System.out.println("3. Find a Country by it's name");
        System.out.println("4. Create and save a Country in db");
        System.out.println("5. Exit the program");

        String input = userReader();

        if (!input.matches("[1-5]+")) {
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
        //calling menu method
        userMenu();
        //closing the connection with the database
        DbConnect.getInstance().close();
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
            //calling menu method
            userMenu();
            //closing the connection with the database
            DbConnect.getInstance().close();
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

        ArrayList<Country> countries;
        String input = userReader();
        countries = daobj.findCountryByName(input);

        if (countries.size() <= 0) {

            System.out.println("Sorry but we could not find " + input + " in our list");
            searchCountryByName();
        } else {

            System.out.println("The result search for the name " + input + " is:");
            System.out.println(countries);
        }
        //calling menu method
        userMenu();
        //closing the connection with the database
        DbConnect.getInstance().close();
    }

    /*
    method creates and save a new country in the db
     */
    private void createCountry() {
        /*
        storing the returns of the methods - the validated inputs of the user in the designated
        variables to be passed as parameters to build a new country obj
        */
        String inputCode = validatingInputCode();

        String inputName = validatingInputName();

        Continent continent = continentChoice();

        Float inputSurfaceArea = validatingInputSurfaceArea();

        String inputHeadOfState = validatingInputHeadOfState();
        /*
        Create a new object country with all the attributes
         */
        country = new Country.BuilderCountry(inputCode, inputName, continent, inputSurfaceArea, inputHeadOfState).build();
        /*
        saving the created new country and printing the message
         */
        daobj.saveCountry(country);
        System.out.println("Well done! the country " + inputName + " is now saved in the system  \n \n \n");
        //calling menu method
        userMenu();
        //closing the connection with the database
        DbConnect.getInstance().close();
    }


    /*
    this method facilitates when choosing the continent.
    It is basically a Switch is a control statement that allows a value to change control of execution.
     */
    public Continent continentChoice() {
        System.out.println("Please pick one of 7 continents to place the country in ? [1-7]: " + "\n1 - Europe\n2 - Asia\n3 - North America\n4 - South America\n5 - Africa\n6 - Oceania\n7 - Antarctica");
        System.out.println("Please enter only one number from 1 to 7.");
        Continent inputContinent = null;

        String option = userReader();
        if (option.isEmpty()) {
            continentChoice();
        }

        if (!option.matches("[1-7]")) {
            continentChoice();
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
        System.out.println("Please start inserting a 1 to 3 digits Country code:  \n");

        Boolean validCode = true;
        String inputCode = userReader();
        //this loop checks the if the user inputs more than 3 digits
        while (validCode) {
            while (inputCode.length() > 3) {
                System.out.println("Remember it must be 1 to 3 digits code. try again: ");
                inputCode = userReader();
            }
            //instantiating a country obj that receives a country obj coming from MySQLCountryDAo
            country = daobj.findCountryByCode(inputCode);
            //checking if the country obj contains the user's input
            if (country.getCode().contains(inputCode)) {
                System.out.println("Sorry, this code already exists in the system. Please try another one: ");
                inputCode = userReader();
            } else {
                validCode = false;

                System.out.println("The code entered was: " + inputCode);
            }
        }
        return inputCode;

    }

    /*
    this method checks if the user inputs only letters when choosing a name for the country
     */
    public String validatingInputName() {
        System.out.println("Please insert the country name: ");
        String inputName = userReader();
        if (!inputName.matches("[a-zA-Z]+")) {
            System.out.println("Sorry, you must insert letters for the country name. Please try again");
            inputName = userReader();
        }
        return inputName;
    }

    /*
    this method checks if the user inputs only integer numbers when setting the surface area
     */
    public Float validatingInputSurfaceArea() {

        System.out.println("Please insert the land area of the country: ");
        String userInput = userReader();
        if (!userInput.matches("[0-9]+")) {
            System.out.println("Sorry, you must insert a number for the land area. Please try again");
            userInput = userReader();
        }
        return Float.parseFloat(userInput);
    }

    /*
    this method checks if the user inputs only letters when setting a name for the head of state
     */
    public String validatingInputHeadOfState() {
        System.out.println("Please insert the name of the head of state");
        String inputHeadOfState = userReader();
        if (!inputHeadOfState.matches("[a-zA-Z ]+")) {
            System.out.println("Sorry, you must insert letters for the head of state name. Please try again");
            inputHeadOfState = userReader();
        }
        return inputHeadOfState;
    }

}



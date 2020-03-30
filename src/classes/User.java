package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User {

        public User() {
            menu();
        }

        public void menu() {
            System.out.println("trying to find a Country?");
            System.out.println("-------------------------");
            System.out.println("Menu Options:");
            System.out.println("1. The list with all Countries");
            System.out.println("2. Find a Country by it's code");
            System.out.println("3. Find a Country by it's name");
            System.out.println("4. Add a new Country to the list");
            System.out.println("5. Exit the program");
            System.out.println("");
            System.out.print("Please select an option from 1-5\r\n");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                int input = Integer.parseInt(br.readLine());

                if(input < 0 || input > 5) {
                    System.out.println("You have entered an invalid selection, please try again\r\n");
                } else if(input == 5) {
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
    public void userMenu (Integer option) {
            MySQLCountryDAO dao = new MySQLCountryDAO();
        switch(option) {
            case 1:
                dao.getCountries();
                break;
            case 2:
                dao.findCountryByCode();
                break;
            case 3:
                dao.findCountryByName();
                break;
            case 4:
                dao.saveCountry();
                break;
            default:
                System.out.println("Have a nice day!");
                System.exit(0);
        }
        menu();
    }
    }


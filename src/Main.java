import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in); // Scanner object for taking input

    public static void main(String[] args) {
        int choice = 3;

        // Ensure necessary files exist or create them
        try {
            File myfile = new File("Account_Info.txt");
            File myfileflights = new File("Flight_Info.txt");

            if (!myfile.exists()) {
                myfile.createNewFile();
                myfileflights.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
            e.printStackTrace(); // Handle unexpected errors
        }

        // Display menu options
        System.out.println("Welcome to the Airline Reservation System!");
        System.out.println("1. Create an Account.");
        System.out.println("2. Login into User Account.");
        System.out.println("3. Login into Admin Console.");
        System.out.println("+--------------------+");
        System.out.println("Enter your Choice: ");

        // Validate user input
        while (true) {
            try {
                choice = input.nextInt(); // Read user choice
                if (choice == 1 || choice == 2 || choice == 3) {
                    break; // Valid input
                }
                System.out.println("Number out of range!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Entry! Enter a number");
            }
            input.nextLine(); // Clear buffer
        }

        // Execute corresponding action based on user choice
        switch (choice) {
            case 1:
                UserAccountCreation userAccountCreation = new UserAccountCreation();
                userAccountCreation.useraccountCreation(); // Create a new user account
                break;
            case 2:
                UserAuthenticator userAuthenticator = new UserAuthenticator();
                userAuthenticator.userauthentication(); // Authenticate existing user
                break;
            case 3:
                AdminAuthenticator adminauthenticator = new AdminAuthenticator();
                adminauthenticator.adminauthenticator(); // Authenticate admin
                break;
            default:
                System.out.println("Choice out of bound!");
                break;
        }
    }
}

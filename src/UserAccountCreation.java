import java.io.*;
import java.util.Scanner;

public class UserAccountCreation {
    // Scanner object for user input
    static Scanner input = new Scanner(System.in);

    // Method to handle user account creation
    public static int useraccountCreation() {
        boolean isnotPresent = true; // Flag to check if the username is already taken

        // Prompt user for a username and password
        System.out.println("Enter your username: ");
        String username = input.next().toLowerCase(); // Convert username to lowercase for uniformity
        System.out.println("Enter your password: ");
        String password = input.next();

        try {
            // Read existing account information to check if the username already exists
            BufferedReader accountinforeader = new BufferedReader(new FileReader("Account_Info.txt"));
            String line = "";

            // Check each line to see if the username is already in use
            while ((line = accountinforeader.readLine()) != null) {
                if (line.contains(username)) {
                    isnotPresent = false; // Username already exists
                    break;
                }
            }
            accountinforeader.close();

        } catch (Exception e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }

        // If the username is not taken, create a new account
        if (isnotPresent) {
            try {
                // Append the new username and password to the file
                BufferedWriter myfilewriter = new BufferedWriter(new FileWriter("Account_Info.txt", true));
                myfilewriter.write(username + "," + password + "\n");
                myfilewriter.newLine(); // Add a new line for the next entry
                myfilewriter.close();
                System.out.println("Account successfully created!");

            } catch (Exception e) {
                System.out.println("An error has occurred: " + e.getMessage());
            }

        } else {
            // If the username already exists, notify the user
            System.out.println("Account already exists! Retry account creation with a unique username.");
            return 1; // Indicate failure to create account due to duplicate username
        }

        return 1; // Indicate success in creating account
    }
}

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAuthenticator {
    // Scanner object for user input
    static Scanner input = new Scanner(System.in);

    // Method to handle user authentication
    public static int userauthentication() {
        int lineCount = 0; // Counter for the number of lines read from the file
        String[] credentials = new String[100]; // Array to store credentials from the file
        Boolean isAuthenticated = false; // Flag to check if the user is authenticated

        try {
            // Display login prompt
            System.out.println("+-------Login Page-------+");
            System.out.println("Enter your Username: ");
            String username = input.next().toLowerCase(); // Convert username to lowercase for consistency
            System.out.println("Enter your Password: ");
            String password = input.next();
            String credent = username.concat("," + password); // Concatenate username and password for comparison

            try {
                // Read existing account information from file
                BufferedReader reader = new BufferedReader(new FileReader("Account_Info.txt"));
                String line;
                // Read each line from the file and store it in the credentials array
                while ((line = reader.readLine()) != null) {
                    credentials[lineCount] = line;
                    lineCount++;
                }
                reader.close();

            } catch (IOException e) {
                e.printStackTrace(); // Print stack trace if there's an I/O error
            }

            // Check if the entered credentials match any of the credentials read from the file
            for (int i = 0; i < lineCount; i++) {
                if (credentials[i].equals(credent)) {
                    isAuthenticated = true; // Set flag to true if credentials match
                }
            }

            if (isAuthenticated) {
                // If authenticated, create a User object and call the appMenu method
                User user = new User();
                user.appMenu(username);
            } else {
                // If authentication fails, notify the user
                System.out.println("Either the username or password is incorrect or both.");
            }

        } catch (InputMismatchException e) {
            // Handle invalid input exceptions
            System.out.println("An error has occurred: Invalid input!");
        }

        return 1; // Indicate the method has completed execution
    }
}

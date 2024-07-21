import java.util.InputMismatchException;
import java.util.Scanner;

public class TypeSelection {
    // Static variable to store the line
    static String line;
    // Scanner object for user input
    static Scanner input = new Scanner(System.in);

    // Method to set the line
    public static void setLine(String line) {
        TypeSelection.line = line;
    }

    // Method to return the seat type based on user input
    public static String seattypereturner() {
        int choice;
        String sent = "none";

        // Prompt user to enter seat type
        System.out.println("Enter the seat type you want to book: ");
        System.out.println("1. Economy");
        System.out.println("2. First Class");
        System.out.println("3. Business");
        System.out.println("+--------------------+");
        System.out.println("Enter your choice: ");

        // Loop until a valid choice is entered
        while (true) {
            try {
                // Read the user's choice
                choice = input.nextInt();

                // Check if the choice is valid
                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                }

                // Print message if number is out of range
                System.out.println("Number out of range!");

            } catch (InputMismatchException e) {
                // Print message if input is not a number
                System.out.println("Invalid Entry! Enter a number");
            }

            // Clear the buffer
            input.nextLine();
        }

        // Split the line into seat types
        String[] seattypes = line.split(",");

        // Determine the seat type based on user choice
        switch (choice) {
            case 1:
                // Set the seat type to Economy
                sent = new String(seattypes[0]);
                break;
            case 2:
                // Set the seat type to First Class
                sent = new String(seattypes[1]);
                break;
            case 3:
                // Set the seat type to Business
                sent = new String(seattypes[2]);
                break;
        }

        // Return the selected seat type
        return sent;
    }
}

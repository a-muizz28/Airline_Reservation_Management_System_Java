import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Flight {
    static Scanner input = new Scanner(System.in); // Scanner object for user input

    // Method to add new flight data
    public static int addExisting() {
        int indicator = 1; // Indicator for continuing the loop

        while (indicator != 0) {
            // Using BufferedWriter to append new flight data to the file
            try (BufferedWriter newinputappend = new BufferedWriter(new FileWriter("Flight_Info.txt", true))) {
                System.out.println("+--------------------+");
                System.out.println("Enter new Flight in proper format as before: ");
                String flight_data_new = input.nextLine(); // Read new flight data
                newinputappend.write(flight_data_new + "\n"); // Write the new flight data to the file
            } catch (IOException e) {
                e.printStackTrace(); // Handle IO exceptions
            }

            // Prompt user for continuation or exit
            while (true) {
                System.out.println("+--------------------+");
                try {
                    System.out.println("Enter 1 to continue to add more flight data and 0 to exit. ");
                    indicator = input.nextInt(); // Read user's choice
                    if (indicator == 0 || indicator == 1) {
                        break; // Valid input, break out of loop
                    }
                    System.out.println("Number out of range!"); // Invalid input message
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Entry! Enter a number"); // Handle invalid input
                }
                input.nextLine(); // Clear the buffer
            }

            System.out.println("Flight Data Added!"); // Confirmation message
            System.out.println("+--------------------+");
        }
        return 1; // Return status
    }

    // Method to update existing flight data
    public static int updateExisting() {
        int linescount = 0; // Counter for the number of lines read
        int flag = 1; // Indicator for continuing the loop
        int index = 1; // Index for selecting which data to update
        String[] flight_data = new String[100]; // Array to store flight data

        try {
            System.out.println("+--------------------+");
            System.out.println("Enter Flight number whose data you want to change: ");
            String flight_number = input.next(); // Read flight number to edit
            input.nextLine(); // Clear the buffer

            // Read existing flight data from the file
            try (BufferedReader flightdata = new BufferedReader(new FileReader("Flight_Info.txt"))) {
                String line;
                while ((line = flightdata.readLine()) != null) {
                    flight_data[linescount] = line; // Store each line in the array
                    linescount++; // Increment line counter
                }
            }

            // Loop until the user decides to stop
            while (flag != 0) {
                for (int i = 0; i < linescount; i++) {
                    // Check if the current line contains the specified flight number
                    if (flight_data[i].contains(flight_number)) {
                        String[] flight_changing = flight_data[i].split(","); // Split the line into components

                        while (true) {
                            System.out.println("+--------------------+");
                            try {
                                System.out.println("Enter the index whose data you want to change: ");
                                index = input.nextInt(); // Read index for the data to change
                                // Validate index input
                                if (index >= 0 && index <= 4) { // Assuming there are 5 fields
                                    break; // Valid index, break out of loop
                                }
                                System.out.println("Number out of range!"); // Invalid index message
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Entry! Enter a number"); // Handle invalid input
                            }
                        }
                        input.nextLine(); // Clear the buffer

                        System.out.println("Enter the new value: ");
                        String newValue = input.nextLine(); // Read the new value
                        flight_changing[index] = newValue; // Update the corresponding index
                        flight_data[i] = String.join(",", flight_changing); // Join the updated components back into a single string

                        // Prompt for continuation or exit
                        while (true) {
                            System.out.println("+--------------------+");
                            try {
                                System.out.println("Enter 1 to add more data and 0 to exit.");
                                flag = input.nextInt(); // Read user's choice
                                if (flag == 0 || flag == 1) {
                                    break; // Valid input, break out of loop
                                }
                                System.out.println("Number out of range!"); // Invalid input message
                            } catch (InputMismatchException e) {

                                System.out.println("Invalid Entry! Enter a number"); // Handle invalid input
                                flag = 0; // Set flag to 0 to exit the loop
                            }
                            input.nextLine(); // Clear the buffer
                        }
                    }
                }
            }

            // Write the updated flight data back to the file
            try (BufferedWriter newflight = new BufferedWriter(new FileWriter("Flight_Info.txt"))) {
                for (int k = 0; k < linescount; k++) {
                    newflight.write(flight_data[k]); // Write each line to the file
                    if (k < linescount - 1) {
                        newflight.newLine(); // Add a new line between entries
                    }
                }
                newflight.close(); // Close the writer
                System.out.println("Flight data updated!"); // Confirmation message
                System.out.println("+--------------------+");
            } catch (IOException ex) {
                ex.printStackTrace(); // Handle IO exceptions
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle IO exceptions
        }
        return 1; // Return status
    }

    public static String[] flightPrices(String username, String flight_number) {
        // Array to store flight price data
        String[] flight_prices = new String[100] , garbage_value = {"0"};
        int linescount = 0;
        boolean isnotPresent = true ;

        try {
            // Ensure the file exists
            File flightprices = new File("Flight_Prices.txt");
            if (!flightprices.exists()) {
                flightprices.createNewFile();
            }

            // Print the flight prices
            System.out.println("Flight Prices:");

            // Read flight price data from the file
            try (BufferedReader flightpricesinfo = new BufferedReader(new FileReader("Flight_Prices.txt"))) {
                String line;
                while ((line = flightpricesinfo.readLine()) != null) {
                    // Print the line if it contains the specified flight number
                    if (line.contains(flight_number)) {

                        System.out.println(line);
                        isnotPresent = false ;

                        TypeSelection typeSelection = new TypeSelection();
                        typeSelection.setLine(line);

                    }

                    // Store the line in the array
                    flight_prices[linescount] = line;
                    linescount++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!isnotPresent) {

            return flight_prices;
        }else{

            System.out.println("Flight number not found!");
            return garbage_value;
        }
    }
}

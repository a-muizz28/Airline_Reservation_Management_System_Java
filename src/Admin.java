import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    static Scanner input = new Scanner(System.in); // Scanner object for taking input

    // Method to display admin menu

    public static int adminMenu(String admin_username) {

        System.out.println("Welcome! " + admin_username);
        int indicator = 1;
        int choice = 4;

        while (indicator != 0) {

            // Display admin menu options
            System.out.println("1. Enter new Flight data.");
            System.out.println("2. Enter new coupons.");
            System.out.println("3. Logout");
            System.out.println("+--------------------+");
            System.out.println("Enter your choice: ");

            while (true) {
                try {
                    choice = input.nextInt(); // Read user input
                    if (choice == 1 || choice == 2 || choice == 3) {
                        break; // Valid input
                    }
                    System.out.println("Number out of range!");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Entry! Enter a number");
                    input.nextLine(); // Clear invalid input
                }
            }

            switch (choice) {
                case 1:
                    newFlightData(admin_username); // Handle new flight data
                    break;
                case 2:
                    couponsAdder(admin_username); // Handle new coupons
                    break;
                case 3:
                    indicator = 0; // Logout
                    System.out.printf("Bye Bye! %s\n", admin_username);
                    break;
                default:
                    System.out.println("Number out of range!");
                    break;
            }

            if (indicator != 0) {
                System.out.println("1 to continue and 0 to exit to the Login Page!");
                while (true) {
                    try {
                        indicator = input.nextInt(); // Read user input
                        if (indicator == 0 || indicator == 1) {
                            break; // Valid input
                        }
                        System.out.println("Number out of range!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Entry! Enter a number");
                        input.nextLine(); // Clear invalid input
                    }
                }
            }
        }

        return indicator;
    }
    //Method to display new flight data
    public static int newFlightData(String username) {
        int ind = 1;
        int choice = 3;

        while (ind != 0) {
            // Display flight data manipulation options
            System.out.println("+--Flight Data Manipulation--+");
            System.out.println("1. To enter new data.");
            System.out.println("2. To change existing data.");
            System.out.println("+--------------------+");
            System.out.println("Enter your choice: ");

            while (true) {
                try {
                    choice = input.nextInt(); // Read user input
                    if (choice == 1 || choice == 2) {
                        break; // Valid input
                    }
                    System.out.println("Number out of range!");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Entry! Enter a number");
                    input.nextLine(); // Clear invalid input
                }
            }

            Flight flight = new Flight();
            switch (choice) {
                case 1:
                    flight.addExisting(); // Add new flight data
                    break;
                case 2:
                    flight.updateExisting(); // Update existing flight data
                    break;
                default:
                    System.out.println("Number out of range!");
                    break;
            }

            while (true) {
                try {
                    System.out.println("Enter 1 to continue and 0 to exit.");
                    ind = input.nextInt(); // Read user input
                    if (ind == 0 || ind == 1) {
                        break; // Valid input
                    }
                    System.out.println("Number out of range!");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Entry! Enter a number");
                    input.nextLine(); // Clear invalid input
                }
            }
        }

        return ind;
    }
    //Method to display coupons related operation
    public static int couponsAdder(String admin_username) {
        int choice = 3;

        try {
            File couponsinfo = new File("Coupons_Info.txt");
            if (!couponsinfo.exists()) {
                couponsinfo.createNewFile(); // Create file if it doesn't exist
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Display coupon data manipulation options
        System.out.println("+--Coupon Data Manipulation--+");
        System.out.println("1. To add new coupon.");
        System.out.println("2. To change existing coupon.");
        System.out.println("+--------------------+");
        System.out.println("Enter your choice: ");

        while (true) {
            try {
                choice = input.nextInt(); // Read user input
                if (choice == 1 || choice == 2) {
                    break; // Valid input
                }
                System.out.println("Number out of range!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Entry! Enter a number");
                input.nextLine(); // Clear invalid input
            }
        }

        Coupons coupons = new Coupons();
        switch (choice) {
            case 1:
                coupons.addCoupons(admin_username); // Add new coupon
                break;
            case 2:
                coupons.updateExistingCoupon(admin_username); // Update existing coupon
                break;
            default:
                break;
        }

        return 1;
    }

}

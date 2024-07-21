import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Coupons {
    static Scanner input = new Scanner(System.in); // Scanner object for taking input

    //Method to add coupons into the files
    public static int addCoupons(String admin_username) {
        try (BufferedWriter couponwriter = new BufferedWriter(new FileWriter("Coupons_Info.txt", true))) {
            int flag = 1;
            while (flag != 0) {
                System.out.println("+--------------------+");
                System.out.println("Enter coupon along with discount with proper format: ");
                String coupon = input.nextLine().toUpperCase(); // Read coupon in uppercase
                couponwriter.write("\n" + coupon); // Write coupon to file
                System.out.println("Coupon Added!");
                System.out.println("+--------------------+");

                while (true) {
                    try {
                        System.out.println("Enter 1 to continue and 0 to exit.");
                        flag = input.nextInt(); // Read user input to continue or exit
                        input.nextLine(); // Clear buffer

                        if (flag == 1 || flag == 0) {
                            break; // Valid input
                        }
                        System.out.println("Number out of range!");

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Entry! Enter a number");
                        input.nextLine(); // Clear invalid input
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
        return 1; // Return value (could be used for further logic)
    }
    //Method to update existing coupons in file
    public static int updateExistingCoupon(String admin_username) {
        String[] coupons_info = new String[100]; // Array to store coupon information
        int linescount = 0;
        int flag = 1;

        try (BufferedReader couponsfilereader = new BufferedReader(new FileReader("Coupons_Info.txt"))) {
            String line;

            // Read all lines from the coupon info file
            while ((line = couponsfilereader.readLine()) != null) {
                coupons_info[linescount] = line;
                linescount++;
            }

            while (flag != 0) {
                System.out.println("+--------------------+");
                System.out.println("Enter coupon code you want to edit: ");
                String coupon_code = input.nextLine(); // Read coupon code to edit

                for (int i = 0; i < linescount; i++) {
                    if (coupons_info[i].contains(coupon_code)) {
                        String[] temp_coupon = coupons_info[i].split(",");
                        System.out.println("Enter index to edit that info: ");
                        int index = input.nextInt(); // Read index to edit
                        input.nextLine(); // Clear buffer
                        System.out.println("Enter the new data: ");
                        temp_coupon[index] = input.nextLine(); // Read new data
                        coupons_info[i] = String.join(",", temp_coupon); // Update coupon info
                    }
                }

                // Write updated coupon info back to file
                try (BufferedWriter writingcoupons = new BufferedWriter(new FileWriter("Coupons_Info.txt"))) {
                    for (int k = 0; k < linescount; k++) {
                        writingcoupons.write(coupons_info[k]);
                        writingcoupons.newLine(); // Write new line
                    }
                }
                System.out.println("Coupon Updated!");
                System.out.println("+--------------------+");

                while (true) {
                    try {
                        System.out.println("Enter 1 to continue and 0 to exit.");
                        flag = input.nextInt(); // Read user input to continue or exit
                        if (flag == 0 || flag == 1) {
                            break; // Valid input
                        }
                        System.out.println("Number out of range!");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Entry! Enter a number");
                        input.nextLine(); // Clear invalid input
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
        return 1; // Return value (could be used for further logic)
    }
}

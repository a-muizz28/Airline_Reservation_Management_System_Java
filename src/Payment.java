import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Payment {
    static Scanner input = new Scanner(System.in);

    // Method to add payment information for a user
    public static int paymentInfo(String username) {
        try {
            // Ensure the payment info file exists
            File paymentinfo = new File("Payment_Info.txt");
            if (!paymentinfo.exists()) {

                paymentinfo.createNewFile();

            }

            input.nextLine(); // Consume newline
            System.out.println("+--------------------+");
            System.out.println("Enter your card number: ");
            String card_number = input.nextLine();
            System.out.println("Enter your CVC: ");
            String card_cvc = input.nextLine();
            System.out.println("Enter your balance: ");
            String balance = input.nextLine();

            // Authenticate the credit card number
            CreditCardAuthentication creditCardAuthentication = new CreditCardAuthentication();

            if (creditCardAuthentication.creditCardAuthentication(card_number)) {
                try (BufferedWriter paymentinfowriter = new BufferedWriter(new FileWriter("Payment_Info.txt", true))) {
                    // Write payment info to the file
                    paymentinfowriter.write(card_number + "," + card_cvc + "," + balance + "," + username);
                    System.out.println("Payment info added!");
                    paymentinfowriter.newLine();
                    paymentinfowriter.flush();
                }
                System.out.println("+--------------------+");
            } else {
                System.out.println("Invalid card number!");
                return 1; // Return error code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1; // Return success code
    }

    // Method to process payment for a flight
    public static Boolean paymentProcedure(String username, String flight_seat_type) {
        String[] coupons_info = new String[100], payment_info = new String[100];
        String[] temp_coupon_holder, temp_card_holder;
        String coupon = "None";
        int discount = 0, linescount = 0, linescount3 = 0, answer = 3;
        Boolean isAuthorized = false;

        System.out.println("+--------------------+");
        System.out.println("Enter your card number: "); // Reads card number
        String card_number = input.nextLine();
        System.out.println("Enter your CVC: "); // Reads cvc
        String card_cvc = input.nextLine();
        System.out.println("+--------------------+");
        System.out.println("Do you have a coupon or not? 1 = yes and 0 = no: ");

        // Validate coupon answer
        while (true) {
            try {
                answer = input.nextInt();
                if (answer == 0 || answer == 1) {
                    break;
                }
                System.out.println("Number out of range!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Entry! Enter a number");
                input.nextLine(); // Clear buffer
            }
        }

        input.nextLine(); // Consume newline
        if (answer == 1) {

            System.out.println("Enter coupon: ");
            coupon = input.nextLine();

        }
        System.out.println("+--------------------+");

        // Read payment and coupon information
        try (BufferedReader paymentinforeader = new BufferedReader(new FileReader("Payment_Info.txt"))) {
            String line;
            while ((line = paymentinforeader.readLine()) != null) {
                payment_info[linescount] = line;
                linescount++;
            }
            try (BufferedReader couponsfilereader = new BufferedReader(new FileReader("Coupons_Info.txt"))) {
                String line2;
                while ((line2 = couponsfilereader.readLine()) != null) {
                    coupons_info[linescount3] = line2;
                    linescount3++;
                }
            }

            // Check for valid coupon and apply discount
            for (int c_i = 0; c_i < linescount3; c_i++) {
                if (coupons_info[c_i].contains(coupon)) {
                    temp_coupon_holder = coupons_info[c_i].split(",");
                    discount = Integer.parseInt(temp_coupon_holder[1]);
                    System.out.println("Your price has been discounted by: " + discount + " dollars");
                }
            }
            System.out.println("+--------------------+");

            // Validate card information and process payment
            for (int i = 0; i < linescount; i++) {
                if (payment_info[i].contains(card_number) && payment_info[i].contains(card_cvc) && payment_info[i].contains(username)) {
                    temp_card_holder = payment_info[i].split(",");
                    int num1 = Integer.parseInt(flight_seat_type.replaceAll("\\D", ""));
                    int balance = Integer.parseInt(temp_card_holder[2]);

                    if (num1 < balance) {
                        isAuthorized = true;
                        System.out.println("Your new price is: " + (num1 - discount));
                        int balance_changed = balance - (num1 - discount);
                        temp_card_holder[2] = Integer.toString(balance_changed);
                        System.out.println("+--------------------+");
                    } else {
                        System.out.println("Low balance");
                        return isAuthorized;
                    }
                    payment_info[i] = String.join(",", temp_card_holder);
                    break;
                }
            }

            // Write updated payment information back to the file
            try (BufferedWriter paymentchangedinfowriter = new BufferedWriter(new FileWriter("Payment_Info.txt"))) {
                for (int k = 0; k < linescount; k++) {
                    paymentchangedinfowriter.write(payment_info[k]);
                    paymentchangedinfowriter.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAuthorized;
    }
}

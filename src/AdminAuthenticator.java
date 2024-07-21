import java.io.*;
import java.util.Scanner;

public class AdminAuthenticator {
    static Scanner input = new Scanner(System.in); // Scanner object for taking input

    //Method to authenticate admin to perform administrative opertaions
    public static int adminauthenticator() {
        int linescount = 0;
        String[] admin_info = new String[100]; // Array to store admin credentials

        try {
            File admininfo_here = new File("Admin_Info.txt");
            if (!admininfo_here.exists()) {
                admininfo_here.createNewFile(); // Create file if it doesn't exist
            }
            System.out.println("+-------Admin Login Page-------+");
            System.out.println("Enter your username: ");
            String admin_username = input.next().toLowerCase(); // Read username
            System.out.println("Enter your password: ");
            String admin_password = input.next().toLowerCase(); // Read password
            String admin_credent = admin_username.concat("," + admin_password); // Concatenate username and password
            System.out.println("+--------------------+");

            try {
                BufferedReader admininforeader = new BufferedReader(new FileReader("Admin_Info.txt"));
                String line;

                // Read all lines from the admin info file
                while ((line = admininforeader.readLine()) != null) {
                    admin_info[linescount] = line;
                    linescount++;
                }
                admininforeader.close();

                // Check if entered credentials match any stored credentials
                for (int i = 0; i < linescount; i++) {

                    if (admin_info[i].equals(admin_credent)) {
                        Admin admin = new Admin();
                        admin.adminMenu(admin_username); // Call admin menu if credentials match
                    } else {
                        System.out.println("Either the username is wrong or the password or both.");
                    }

                }

            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage()); // Handle exceptions
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }
        return 1; // Return value (could be used for further logic)
    }
}

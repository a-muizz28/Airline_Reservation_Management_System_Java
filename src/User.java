import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

    public class User {
    static Scanner input = new Scanner(System.in); // Scanner object
        // Method to display main menu
        public static int appMenu(String username){

        int indicator = 1 , choice = 6;
        System.out.println("Hello! " + username); // Greets the user

        while(indicator != 0) {

            // Main menu
            System.out.println("1.Book a flight. ");
            System.out.println("2.Cancel a flight");
            System.out.println("3.Check your booked flights.");
            System.out.println("4.Change Password.");
            System.out.println("5.To add card info.");
            System.out.println("6.Logout");
            System.out.println("+--------------------+");
            System.out.println("Enter your choice: ");

            while (true) {

                try {

                    choice = input.nextInt();
                    if (choice == 1 || choice == 2 || choice == 3 || choice == 4 ||  choice == 5 || choice == 6) {
                        break;
                    }
                    System.out.println("Number out of range!"); // Invalid number

                } catch (InputMismatchException e) {
                    System.out.println("Invalid Entry! Enter a number"); // Invalid datatype

                }
                input.nextLine();

            }
                    switch (choice) {
                        case 1:
                            multipleFlights(username);
                            break;
                        case 2:
                            cancelseat(username);
                            break;
                        case 3:
                            checkBooking(username);
                            break;
                        case 4:
                            changePassword(username);
                            break;
                        case 5:
                            Payment payment = new Payment();
                            payment.paymentInfo(username);
                            break;
                        case 6:
                            System.out.println("Bye Bye! " + username);
                            return 1;
                        default:
                            System.out.println("Choice out of Bound!");
                            break;
                    }

            while (true) {
                System.out.println("+--------------------+");
                try {
                    System.out.println("Enter 1 to reload main App menu and 0 to exit: "); // reloads the above menu for multiple times of operation
                    indicator = input.nextInt();
                    if (indicator == 0 || indicator == 1) {

                        break;
                    }

                    System.out.println("Number out of range!");

                } catch (InputMismatchException e) {
                    System.out.println("Invalid Entry! Enter a number");
                    indicator = 1 ;
                }
                input.nextLine();
            }
        }
        return 1;
    }
    // Method to display flights
    public static int multipleFlights(String username){
        int linescount = 0 ;
        String [] flights_info = null ;

            try {
                BufferedReader myfilereader = new BufferedReader(new FileReader("Flight_Info.txt"));
                while (myfilereader.readLine() != null) {
                    linescount++;
                }
                myfilereader.close();
                BufferedReader myfile_r = new BufferedReader(new FileReader("Flight_Info.txt"));
                String line;
                flights_info = new String[linescount];
                int i = 0;
                while ((line = myfile_r.readLine()) != null) {
                    flights_info[i] = line;
                    i++;
                }
                myfile_r.close();
                System.out.println("+--------------------+");
                System.out.println("Flight Number|Departure|Destination|Time of Departure|Seats Available"); // Displays flights
                for (String Flights : flights_info) {
                    System.out.println(Flights);
                }

                selectseat(username , flights_info);

            // for handling file exception
            }catch (Exception e){
                System.out.println("An error has occurred!" +e.getMessage());
            }
            System.out.println("+--------------------+");

        return 1;

    }
    // Method to select flight
    public static int selectseat(String username , String [] flights_info){

        String flight_seat_type = null;

        System.out.println("+--------------------+");
        input.nextLine();
        System.out.println("Enter the Flight Number to book a flight: ");
        String flight_number = input.nextLine();

        Flight flights = new Flight();
        String [] flightprices = flights.flightPrices(username , flight_number);

        if(flightprices[0] != "0") {

            TypeSelection typeSelection = new TypeSelection();
            flight_seat_type = new String(typeSelection.seattypereturner());

        }

        Payment payment = new Payment();
        Boolean isAuthorized = payment.paymentProcedure(username , flight_seat_type); // for payment procedure

        String Details = "";
        Boolean flightFound = false;

        if(isAuthorized) {

            try {

                File mybookinfo = new File("Booking_Info.txt");
                if (!mybookinfo.exists()) {
                    mybookinfo.createNewFile();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                int linescount3 = 0;
                for( int x = 0 ; x < flights_info.length ; x++){
                    if(flights_info[x] != null){
                        linescount3 ++ ;
                    }
                }
                for (int i = 0; i < linescount3; i++) { // reduces the number of seats in the file when one books a seat
                    String flight = flights_info[i];
                    String[] temp_flights_holder = flight.split(",");

                    if (temp_flights_holder[0].equals(flight_number)) {
                        Details = flight;
                        flightFound = true;
                        int seats = Integer.parseInt(temp_flights_holder[4].trim());
                        if (seats > 0) {
                            seats--;
                            temp_flights_holder[4] = Integer.toString(seats);
                            flights_info[i] = String.join(",", temp_flights_holder);
                        } else {
                            System.out.println("No seats available for this flight.");
                        }
                        break;
                    }
                }

                if (flightFound) {

                    try {
                        BufferedWriter newflightdata = new BufferedWriter(new FileWriter("Flight_Info.txt"));
                        for (int i = 0; i < linescount3; i++) {
                            newflightdata.write(flights_info[i] + "\n");
                        }
                        newflightdata.close();
                    }catch (Exception e){
                        System.out.println("Something went wrong " + e.getMessage());
                    }

                    int linescount2 = 0;
                    for(int h = 0 ; h < flightprices.length ; h++){
                        if(flightprices[h] != null){
                            linescount2++ ;
                        }
                    }

                    try {

                        Boolean isPresent = false;
                        BufferedWriter mybookinfowriter = new BufferedWriter(new FileWriter("Booking_Info.txt", true));
                        for (int m = 0; m < linescount2 ; m++) {
                            if (flightprices[m].contains(flight_number) && flightprices[m].contains(flight_seat_type)) {
                                isPresent = true;
                            }
                        }
                        if (isPresent) {
                            mybookinfowriter.write("Flight details: " + Details + "," + flight_seat_type + " Booked for: " + username + "\n");
                            mybookinfowriter.close();
                            System.out.println("Your FLight has been booked successfully!");
                        } else {
                            System.out.println("Something went wrong!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Flight Number not found!");
                }

        }else{
            System.out.println("Payment gone wrong!");
        }
        System.out.println("+--------------------+");
        return 1;

    }
    // Method to cancel seat
    public static int cancelseat(String username){
        String Details = "";
        boolean flightFound = false , flightFound2 = false;
        int linescount = 0;
        String[] flights_info = null , booking_info = new String[100];

        System.out.println("+--------------------+");
        System.out.println("Enter the Flight Number to cancel a flight: ");
        String flight_number = input.next();

        try {

            BufferedReader myfilereader = new BufferedReader(new FileReader("Flight_Info.txt"));
            while (myfilereader.readLine() != null) {
                linescount++;
            }
            myfilereader.close();

            flights_info = new String[linescount];
            BufferedReader myfile_r = new BufferedReader(new FileReader("Flight_Info.txt"));
            String line;
            int i = 0;
            while ((line = myfile_r.readLine()) != null) {
                flights_info[i] = line;
                i++;
            }
            myfile_r.close();

            for (int j = 0; j < flights_info.length; j++) { // increase the number of seats incase a seat is cancelled
                String flight = flights_info[j];
                String[] temp_flights_holder = flight.split(",");
                if (temp_flights_holder[0].equals(flight_number)) {
                    Details = flight;
                    flightFound = true;
                    int seats = Integer.parseInt(temp_flights_holder[4].trim());
                    seats++;
                    temp_flights_holder[4] = Integer.toString(seats);
                    flights_info[j] = String.join(",", temp_flights_holder);
                    break;
                }
            }

            if (flightFound) {
                BufferedWriter newflightdata = new BufferedWriter(new FileWriter("Flight_Info.txt"));
                for (int j = 0; j < flights_info.length; j++) {
                    newflightdata.write(flights_info[j] + "\n");
                }
                newflightdata.close();

                int line_count = 0;
                BufferedReader mybookinginfo = new BufferedReader(new FileReader("Booking_Info.txt"));
                while ((line = mybookinginfo.readLine()) != null) {
                    booking_info[line_count] = line;
                    line_count++;
                }
                mybookinginfo.close();

                for (int j = 0; j < line_count; j++) {
                    String booking = booking_info[j];
                    if (booking.contains("Flight details: " + flight_number) && booking.contains("Booked for: " + username)) {
                        booking_info[j] = "";
                        flightFound2 = true;
                    }
                }

                if (flightFound2) {
                    BufferedWriter newbookingdata = new BufferedWriter(new FileWriter("Booking_Info.txt"));
                    for (int j = 0; j < line_count; j++) {
                        if (!booking_info[j].trim().isEmpty()) {
                            newbookingdata.write(booking_info[j] + "\n");
                        }
                    }
                    newbookingdata.close();
                    System.out.printf("Your flight %s has been cancelled successfully!\n", Details);
                } else {
                    System.out.println("Booking for the flight not found!");
                }
            } else {
                System.out.println("Flight Number not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("+--------------------+");
        return 1;
    }
    // Method to check booking
    public static void checkBooking(String username){
        Boolean isBooked = false;

        try {

            BufferedReader booking_file_reader = new BufferedReader(new FileReader("Booking_Info.txt"));
            String line ;

            System.out.println("+--------------------+");
            System.out.println("The Flights Booked under your name are: \n");
            while((line = booking_file_reader.readLine()) != null){ // Reads booking from the file in your name
                if( line.contains("Booked for: " + username)){
                    System.out.println(line);
                    isBooked = true;
                }
            }
            if(!isBooked){
                System.out.printf("I couldn't find any booking under your name %s: " ,username);
            }

        }catch(Exception e){
            System.out.println("Something went wrong!" + e.getMessage());
        }
    }
    // Method to change user password
    public static int changePassword( String username){
        int linecount = 0 ;
        String [] account_info = new String[100] ;

        System.out.println("+--------------------+");
        System.out.println("Enter your existing password: ");
        try {
            String password = input.next();
            String credent = username + "," + password;
            try {
                BufferedReader accountfilereader = new BufferedReader(new FileReader("Account_Info.txt"));
                String line;
                while ((line = accountfilereader.readLine()) != null) { // Reads the account info file
                    account_info[linecount] = line ;
                    linecount++;
                }
                accountfilereader.close();
                Boolean isChanged = false ;
                for (int k = 0 ; k < linecount ; k++){
                    if (account_info[k].equals(credent)){
                        System.out.println("Enter your new Password: "); // Reads new password
                        String password_new = input.next();
                        String credent_new = username.concat("," + password_new);
                        account_info[k] = credent_new ;
                        isChanged = true ;
                    }
                }
                if(!isChanged){
                    System.out.println("Your Password couldn't be changed!");
                }

                try{
                    BufferedWriter updatedaccountinfo = new BufferedWriter(new FileWriter("Account_Info.txt"));
                    for (int l = 0 ; l < linecount ; l++){
                        updatedaccountinfo.write(account_info[l] + "\n"); // updates new password in file
                    }
                    updatedaccountinfo.close();
                    System.out.println("Password for: " + username + " has been changed!");
                }catch(Exception e){
                    e.printStackTrace();
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }catch(InputMismatchException e){
            System.out.println("An error occurred " + e.getMessage());
        }
        System.out.println("+--------------------+");
        return 1;

    }
}

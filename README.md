# Airline Reservation System

-> Overview

This project is a console-based airline reservation system implemented in Java. The system allows users to book flights, cancel bookings, check their booked flights, change their passwords, and manage their payment information. 

-> Features

1. **User Authentication**: Users can log in to the system with a username and password.
2. **Flight Booking**: Users can view available flights and book seats.
3. **Cancel Booking**: Users can cancel their booked flights.
4. **Check Bookings**: Users can view all flights they have booked.
5. **Change Password**: Users can change their account password.
6. **Payment Management**: Users can add or update their payment information.

-> File Structure

- `User.java`: Contains the main user functionalities including booking flights, canceling flights, checking bookings, changing passwords, and managing payment information.
- `Prices.java`: Handles the pricing details for different flights.
- `Payment.java`: Manages the payment process and stores payment information.
- `Account_Info.txt`: Stores user account information in the format `username,password`.
- `Flight_Info.txt`: Contains information about available flights in the format `Flight Number,Departure,Destination,Time of Departure,Seats Available`.
- `Booking_Info.txt`: Stores booking information for users.


-> Methods

- `appMenu(String username)`: Displays the main menu and handles user choices.
- `multipleFlights(String username)`: Displays available flights and allows the user to select one.
- `selectFlight(String username, String[] flights_info)`: Allows the user to select and book a flight.
- `cancelFlights(String username)`: Allows the user to cancel a booked flight.
- `checkBooking(String username)`: Displays all flights booked by the user.
- `changePassword(String username)`: Allows the user to change their account password.

-> Admin Methods
- **Add Flight**: Admins can add new flights to the system.
- **Delete Flight**: Admins can delete existing flights from the system.
- **View All Bookings**: Admins can view all bookings made by users.
- **Manage User Accounts**: Admins can manage user accounts including adding and deleting users.

-> Usage

To run the application, start from the main file.


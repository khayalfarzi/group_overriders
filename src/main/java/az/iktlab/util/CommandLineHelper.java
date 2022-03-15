package az.iktlab.util;

public class CommandLineHelper {
    public static void showAppMenuBar() {
        System.out.println(ConsoleColors.BLUE + "Commands:\n" +
                "1.OnlineBoard - Shows information about all flights in the next 24 hours.\n" +
                "2.ShowFlightInfo - Information about the flight whose ID is entered.\n" +
                "3.SearchAndBooking - Search and booking a flight.\n" +
                "4.CancelBooking - Cancellation of reservations made by the user.\n" +
                "5.MyFlights - A list of all bookings in which the user is a passenger.\n" +
                "6.Logout - to log out of your account.\n");
    }

    public static void showLoginMenuBar() {
        System.out.println(ConsoleColors.BLUE + "Commands:\n" +
                "1.Login - for log in if you have a user account.\n" +
                "2.Registration - for register to create a new account.\n" +
                "3.Exit - for shut down the application.\n");
    }

    public static void showSearchAndBookingMenuBar() {
        System.out.println(ConsoleColors.BLUE + "Commands:\n" +
                "1.Search - for search all available flights.\n" +
                "2.Booking - for booking any available flights.\n");
    }
}

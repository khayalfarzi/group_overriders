package az.iktlab.util;

import az.iktlab.controller.BookingController;
import az.iktlab.controller.FlightController;
import az.iktlab.controller.UserController;
import az.iktlab.dao.repo.BookingDao;
import az.iktlab.dao.repo.FlightDao;
import az.iktlab.dao.repo.UserDao;
import az.iktlab.model.Booking;
import az.iktlab.model.Flight;
import az.iktlab.model.Gender;
import az.iktlab.service.BookingService;
import az.iktlab.service.FlightService;
import az.iktlab.service.UserService;
import az.iktlab.util.Commands.AppMenuCommands;
import az.iktlab.util.Commands.LoginPageCommands;
import az.iktlab.util.Commands.SearchBookingCommands;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    public static String doHashPassword(String password) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] resultArr = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte c : resultArr) {
                sb.append(String.format("%02x", c));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Gender validateGender(String gender) {
        String genderLowerCase = gender.toLowerCase();
        if (genderLowerCase.equals("male"))
            return Gender.MALE;
        else if (genderLowerCase.equals("female"))
            return Gender.FEMALE;
        else return Gender.FALSE_GENDER;
    }

    public static boolean checkUsername(String username) {
        boolean checker = true;
        String s = Character.toString(username.charAt(0));
        if (username.length() < 8) {
            System.out.println(ConsoleColors.RED +
                    "Username cannot be less than 8 digits.\n");
            checker = false;
        } else if (Pattern.matches("[a-z]", s) == false) {
            System.out.println(ConsoleColors.RED +
                    "Username can only start with a lowercase letter.\n");
            checker = false;
        }
        return checker;
    }

    public static boolean checkUsernamePattern(String username) {
        int n = username.length();
        boolean checker = true;
        for (int j = 0; j < n; j++) {
            String s = Character.toString(username.charAt(j));
            if (Pattern.matches("[ ]", s) == true) {
                System.out.println(ConsoleColors.RED +
                        "You cannot use spaces in the username.\n");
                checker = false;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            String s = Character.toString(username.charAt(i));
            if (Pattern.matches("[a-z0-9_.]", s) == false) {
                System.out.println(ConsoleColors.RED +
                        "Only lowercase letters, numbers, underscores(_) and dot(.)" +
                        " can be used in the username.\n");
                checker = false;
                break;
            }
        }
        return checker;
    }

    public static boolean checkAge(int age) {
        boolean checker = true;
        if (age < 18) {
            System.out.println(ConsoleColors.RED +
                    "Persons under the age of 18 cannot register.\n");
            checker = false;
        }
        return checker;
    }

    public static boolean checkFirstAndLastName(String name) {
        String s = Character.toString(name.charAt(0));
        boolean checker = true;
        if (Pattern.matches("[a-z]", s) == true) {
            System.out.println(ConsoleColors.RED +
                    "First name or last name can only start with a Uppercase letter.\n");
            checker = false;
        } else {
            int n = name.length();
            for (int j = 0; j < n; j++) {
                String str = Character.toString(name.charAt(j));
                if (Pattern.matches("[ ]", str) == true) {
                    System.out.println(ConsoleColors.RED +
                            "You cannot use spaces in the last name and first name.\n");
                    checker = false;
                    break;
                }
            }
        }
        return checker;
    }

    public static boolean checkPassword(String password) {
        String s = Character.toString(password.charAt(0));
        boolean checker = true;
        if (password.length() < 8) {
            System.out.println(ConsoleColors.RED +
                    "Password cannot be less than 8 digits.\n");
            checker = false;
        } else if (Pattern.matches("[A-Z]", s) == false) {
            System.out.println(ConsoleColors.RED +
                    "Password can only start with a Uppercase letter.\n");
            checker = false;
        }
        return checker;
    }

    public static boolean checkPasswordPattern(String password) {
        int n = password.length();
        boolean checker = true;
        for (int j = 0; j < n; j++) {
            String s = Character.toString(password.charAt(j));
            if (Pattern.matches("[ ]", s) == true) {
                System.out.println(ConsoleColors.RED +
                        "You cannot use spaces in the password.\n");
                checker = false;
            }
        }

        for (int i = 0; i < n; i++) {
            String s = Character.toString(password.charAt(i));
            if (Pattern.matches("[a-zA-Z0-9!@#$%^&*]", s) == false && checker) {
                System.out.println(ConsoleColors.RED +
                        "You cannot use the " + s + " symbol in the password.");
                System.out.println(ConsoleColors.RED + "password can contain upper" +
                        " and lower case letters, numbers and ! @ # $ % ^ & * characters.\n");
                checker = false;
            }
        }
        return checker;
    }

    public static boolean checkUsernameInDatabase(String username) {
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        UserController userController = new UserController(userService);
        boolean checker = false;
        int count = userController.checkUsernameInDatabase(username);
        if (count > 0) {
            System.out.printf(ConsoleColors.RED + "Username '%s' is used," +
                    "you cannot get this name.\n\n", username);
        } else {
            checker = true;
        }
        return checker;
    }

    public static boolean checkEmptySeats(long fightId) {
        boolean checker = false;
        FlightDao flightDao = new FlightDao();
        FlightService flightService = new FlightService(flightDao);
        FlightController flightController = new FlightController(flightService);
        int count = flightController.countEmptySeats(fightId);
        if (count > 0) checker = true;
        else
            System.out.printf(ConsoleColors.RED + "There is no empty seats on the flight" +
                    " with ID %s.\n\n", fightId);
        return checker;
    }

    public static boolean getCountSearchResult(Flight flight) {

        FlightDao flightDao = new FlightDao();
        FlightService flightService = new FlightService(flightDao);
        FlightController flightController = new FlightController(flightService);
        int count = flightController.getCountSearchResult(flight);
        if (count == 0) {
            System.out.printf(ConsoleColors.RED + "%s results found for your search.\n", count);
            System.out.println(ConsoleColors.RESET);
            return false;
        } else if (count > 0) {
            System.out.printf(ConsoleColors.GREEN + "%s results found for your search.\n", count);
            System.out.print(ConsoleColors.RESET);
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPassengerBookingTable(Booking booking) {
        BookingDao bookingDao = new BookingDao();
        BookingService bookingService = new BookingService(bookingDao);
        BookingController bookingController = new BookingController(bookingService);

        int count = bookingController.checkPassengerBookingTable(booking);

        if (count > 0) {
            return false;
        } else if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkGender(Gender gender) {
        boolean checker = true;
        if (gender != Gender.FEMALE) {
            if (gender != Gender.MALE) {
                System.out.println(ConsoleColors.RED +
                        "Enter valid gender name.\n");
                checker = false;
            }
        }
        return checker;
    }

    public static boolean checkCityFirstLetter(String destination) {
        String s = Character.toString(destination.charAt(0));
        boolean checker = true;
        if (Pattern.matches("[A-Z]", s) == false) {
            System.out.println(ConsoleColors.RED +
                    "City name can only start with a Uppercase letter.\n");
            System.out.print(ConsoleColors.RESET);
            checker = false;
        }
        return checker;
    }

    public static boolean checkFlightIdInDatabase(Long flightId) {
        FlightDao flightDao = new FlightDao();
        FlightService flightService = new FlightService(flightDao);
        Long id = flightService.checkFlightIdInDatabase(flightId);
        if (id > 0)
            return true;
        else {
            System.out.println(ConsoleColors.RED +
                    "No flight found matching the ID entered.\n");
            System.out.print(ConsoleColors.RESET);
            return false;
        }
    }

    public static boolean checkBookingId(long bookingId) {
        BookingDao bookingDao = new BookingDao();
        BookingService bookingService = new BookingService(bookingDao);
        BookingController bookingController = new BookingController(bookingService);

        int count = bookingController.checkBookingIdInDatabase(bookingId);
        if (count == 0) {
            System.out.printf(ConsoleColors.RED +
                    "No bookings with booking id equal to %s were found.\n\n", bookingId);
            System.out.print(ConsoleColors.RESET);
            return false;
        } else
            return true;
    }

    public static long getFlightIdCancelBooking(long bookingId) {
        BookingDao bookingDao = new BookingDao();
        BookingService bookingService = new BookingService(bookingDao);
        BookingController bookingController = new BookingController(bookingService);

        List<Booking> listBooking = bookingController.getFlightIdCancelBooking(bookingId);
        return listBooking.get(0).getFlightId();
    }

    public static AppMenuCommands getAppMenuCommandName(String commandNumber) {
        AppMenuCommands result = null;

        for (AppMenuCommands command : AppMenuCommands.values()) {
            if (command.getCommandNumber().equals(commandNumber)) {
                result = command;
                break;
            }
        }

        return result;
    }

    public static LoginPageCommands getLoginPageCommandName(String commandNumber) {
        LoginPageCommands result = null;

        for (LoginPageCommands command : LoginPageCommands.values()) {
            if (command.getCommandNumber().equals(commandNumber)) {
                result = command;
                break;
            }
        }
        return result;
    }

    public static SearchBookingCommands getSearchBookingCommandName(String commandNumber) {
        SearchBookingCommands result = null;

        for (SearchBookingCommands command : SearchBookingCommands.values()) {
            if (command.getCommandNumber().equals(commandNumber)) {
                result = command;
                break;
            }
        }
        return result;
    }
}

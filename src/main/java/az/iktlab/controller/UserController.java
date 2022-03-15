package az.iktlab.controller;

import az.iktlab.model.User;
import az.iktlab.service.UserService;
import az.iktlab.util.ConsoleColors;
import az.iktlab.util.Validator;

import java.util.Scanner;

public class UserController {
    private final Scanner sc = new Scanner(System.in);
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public String loginUser() {
        System.out.print(ConsoleColors.RESET +
                "\nFill in the fields to login to the app:\n");

        System.out.print(ConsoleColors.RESET + "Enter username:");
        String username = sc.nextLine();

        System.out.print(ConsoleColors.RESET + "Enter password:");
        String password = sc.nextLine();

        return service.loginUser(username, password);
    }

    public int checkUsernameInDatabase(String username) {
        return service.checkUsernameInDatabase(username);
    }

    public void registrationUser() {
        System.out.print(ConsoleColors.RESET +
                "\nFill in the fields to registration to the app:\n");

        User user = new User();

        System.out.print("Enter username:");
        String username = sc.nextLine();
        user.setUsername(username);

        System.out.print("Enter password:");
        String password = sc.nextLine();
        user.setPassword(password);

        System.out.print("Enter first name:");
        String firstName = sc.nextLine();
        user.setFirstName(firstName);

        System.out.print("Enter last name:");
        String lastName = sc.nextLine();
        user.setLastName(lastName);

        System.out.print("Enter age:");
        int age = sc.nextInt();
        user.setAge(age);
        sc.nextLine();

        System.out.print("(Example for gender:male/female) Enter gender:");
        String gender = sc.nextLine();
        user.setGender(Validator.validateGender(gender));

        service.registrationUser(user);

    }
}

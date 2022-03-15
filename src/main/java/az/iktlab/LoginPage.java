package az.iktlab;

import az.iktlab.dao.repo.StartupLoaderDao;
import az.iktlab.dao.repo.UserDao;
import az.iktlab.service.UserService;
import az.iktlab.controller.UserController;
import az.iktlab.util.CommandLineHelper;
import az.iktlab.util.ConsoleColors;
import az.iktlab.util.Commands.LoginPageCommands;
import az.iktlab.util.Validator;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginPage {
    public static void runLoginPage() throws SQLException {
//        StartupLoaderDao startupLoaderDao = new StartupLoaderDao();
//        startupLoaderDao.createTables();
        final Scanner sc = new Scanner(System.in);
        System.out.println(ConsoleColors.RESET +
                "Welcome to the application:\n");

        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        UserController userController = new UserController(userService);

        while (true) {
            CommandLineHelper.showLoginMenuBar();
            System.out.print(ConsoleColors.RESET + "Select command:");
            String commandNumber = sc.nextLine();
            LoginPageCommands command = Validator.getLoginPageCommandName(commandNumber);
            if (command != null) {
                switch (command) {
                    case LOGIN:
                        String username = null;
                        username = userController.loginUser();
                        if (username != null) {
                            Application.runApplication(username);
                        }
                        break;
                    case REGISTRATION:
                        userController.registrationUser();
                        break;
                    case EXIT:
                        System.out.println(ConsoleColors.RESET + "Goodbye, see you again");
                        return;
                }
            } else {
                System.out.printf(ConsoleColors.RED + "%s is invalid Command!\n\n", commandNumber);
            }

        }
    }
}

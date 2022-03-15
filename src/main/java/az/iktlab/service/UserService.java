package az.iktlab.service;

import az.iktlab.dao.entity.UserEntity;
import az.iktlab.dao.repo.UserDao;
import az.iktlab.mapper.UserMapper;
import az.iktlab.model.User;
import az.iktlab.util.ConsoleColors;
import az.iktlab.util.Validator;

import java.sql.SQLException;

public class UserService {
    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public String loginUser(String username, String pass) {
        if (username.equals("") || username.equals(" ")) {
            System.out.println(ConsoleColors.RED +
                    "Username and password field must be filled.\n");
            return null;
        } else if (pass.equals("") || pass.equals(" ")) {
            System.out.println(ConsoleColors.RED +
                    "Username and password field must be filled.\n");
            return null;
        }

        String password = Validator.doHashPassword(pass);

        int count = 0;
        try {
            count = dao.loginUser(username, password);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "An error occurred while logging in.");
            System.out.println(ConsoleColors.RED +
                    "Error message: %s" + e.getMessage() + "\n");
        }
        if (count > 0) {
            System.out.println(ConsoleColors.GREEN +
                    "You have successfully logged in.\n");
            return username;
        } else {
            System.out.println(ConsoleColors.RED +
                    "username or password is incorrect.\n");
            return null;
        }
    }

    public int checkUsernameInDatabase(String username) {
        int count = 0;
        try {
            count = dao.checkUsernameInDatabase(username);
        } catch (SQLException e) {
            System.out.println("An error occurred while registration.");
            System.out.println("Error message: %s" + e.getMessage() + "\n");
        }
        return count;
    }

    public void registrationUser(User user) {
        //check that the username is set correctly
        if (Validator.checkUsername(user.getUsername()) == false) return;
        if (Validator.checkUsernamePattern(user.getUsername()) == false) return;
        if (Validator.checkUsernameInDatabase(user.getUsername()) == false) return;

        //check that the password is set correctly
        if (Validator.checkPassword(user.getPassword()) == false) return;
        if (Validator.checkPasswordPattern(user.getPassword()) == false) return;

        //convert password to md5 format
        user.setPassword(Validator.doHashPassword(user.getPassword()));

        if (Validator.checkFirstAndLastName(user.getFirstName()) == false) return;
        if (Validator.checkFirstAndLastName(user.getLastName()) == false) return;
        if (Validator.checkAge(user.getAge()) == false) return;
        if (Validator.checkGender(user.getGender()) == false) return;


        UserEntity userEntity = UserMapper.mapToEntity(user);

        try {
            boolean flag = dao.registrationUser(userEntity);
            if (flag) System.out.println(ConsoleColors.GREEN +
                    "You have successfully registered.\n");
            else System.out.println(ConsoleColors.RED +
                    "registration failed.\n");
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED +
                    "An error occurred while registration.");
            System.out.printf(ConsoleColors.RED +
                    "Error message: %s" + e.getMessage() + "\n");
        }

    }

}

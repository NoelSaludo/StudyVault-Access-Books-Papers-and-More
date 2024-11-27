package FinalProject.Controller;

import java.util.Scanner;

import FinalProject.Model.Login;
import FinalProject.Model.Data.User;
import FinalProject.View.LoginView;

public class LoginController {
    Login login;
    LoginView view;

    public LoginController(Login login, LoginView view) {
        this.login = login;
        this.view = view;
    }

    public User Login(Scanner in) {
        User loggingUser = null;
        while (loggingUser == null) {
            try {
                String username, password;
                view.cancelOption();
                this.view.showUsername();
                username = in.nextLine();
                this.view.showPassword();
                password = in.nextLine();
                if (username.equals("0") || password.equals("0"))
                    break;
                loggingUser = this.login.findUser(username, password);
                if (loggingUser == null) {
                    view.errorMessage();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return loggingUser;
    }

    /**
     * Login function for the Admin that returns a User from the admin table
     *
     * @return User
     * */
    public User LoginAdmin(Scanner in) {
        User loggingUser = null;
        while (loggingUser == null) {
            try {
                view.cancelOption();
                this.view.showUsername();
                String username = in.nextLine();
                this.view.showPassword();
                String password = in.nextLine();
                if (username.equals("0") || password.equals("0"))
                    break;
                loggingUser = this.login.findAdmin(username, password);
                if (loggingUser == null) {
                    view.errorMessage();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return loggingUser;
    }
}

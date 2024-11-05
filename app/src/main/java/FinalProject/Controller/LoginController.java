package FinalProject.Controller;

import java.util.Scanner;

import FinalProject.Model.Login;
import FinalProject.Model.Data.User;
import FinalProject.View.LoginView;

public class LoginController {
    Login login;
    LoginView view;
    Scanner in;
 
    public LoginController(Login login, LoginView view) {
        this.login = login;
        this.view = view;
        in = new Scanner(System.in);
    }

    /**
     * It returns a user that is validated to exist in the database and determines
     * if it is a Learner or a Educator
     * 
     * @return User
     */
    public User showLogin() {
        User loggingUser = null;
        while (loggingUser == null) {
            try {
                String username, password;
                System.out.println("Enter 0 to cancel login");
                this.view.showUsername();
                username = in.nextLine();
                this.view.showPassword();
                password = in.nextLine();
                if (username.equals("0") || password.equals("0"))
                    break;
                loggingUser = this.login.findUser(username, password);
                if (loggingUser == null) {
                    System.out.println("Login error try again");
                }
            } catch (Exception e) {
                System.out.println("Unexcpected Error in showLogin");
            }
        }
        return loggingUser;
    }

}

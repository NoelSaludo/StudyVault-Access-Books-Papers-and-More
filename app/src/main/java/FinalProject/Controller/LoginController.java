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

    /**
     * It returns a user that is validated to exist in the database and determines if it is a Learner or a Educator
     * @return User
     * */
    public User showLogin() {
        User loggingUser;
        String username, password;
        Scanner in = new Scanner(System.in);
        this.view.showUsername();
        username = in.next();
        this.view.showPassword();
        password = in.next();
        loggingUser = this.login.findUser(username, password);
        System.out.println(loggingUser.getClass());
        in.close();
        return loggingUser;

    }

}

package FinalProject.Controller;

import java.util.Scanner;

import FinalProject.Model.Login;
import FinalProject.Model.Data.User;
import FinalProject.Utils.DataCollector;
import FinalProject.View.LoginView;

import javax.xml.crypto.Data;

public class LoginController {
    Login login;
    LoginView view;
    User user;

    public LoginController(Login login, LoginView view) {
        this.login = login;
        this.view = view;
        user = null;
    }

    public User Login(Scanner in) {
        DataCollector dc = new DataCollector();
        String[] labels = {view.getUsernameLabel(), view.getPasswordLabel()};
        view.cancelOption();
        String[] data = dc.getData(labels,in);
        if (data == null) return null;
        user = login.findUser(data[0], data[1]);
        return user;
    }

    /**
     * Login function for the Admin that returns a User from the admin table
     *
     * @return User
     */
    public User LoginAdmin(Scanner in) {
        DataCollector dc = new DataCollector();
        String[] labels = {view.getUsernameLabel(), view.getPasswordLabel()};
        view.cancelOption();
        String[] data = dc.getData(labels,in);
        if (data == null) return null;
        user = login.findAdmin(data[0], data[1]);
        return user;
    }
}

package FinalProject.Controller;

import FinalProject.Model.Login;
import FinalProject.View.LoginView;

public class LoginController {
    Login login;
    LoginView view;

    public LoginController(Login login, LoginView view) {
        this.login = login;
        this.view = view;
    }

}

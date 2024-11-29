package FinalProject.Controller;

import java.util.Scanner;

import FinalProject.Model.Register;
import FinalProject.Model.Data.User;
import FinalProject.View.RegisterView;

public class RegisterController {
    private Register register;
    private RegisterView view;

    public RegisterController(Register register, RegisterView view) {
        this.register = register;
        this.view = view;
    }

    public void Register(Scanner in) {
        User newUser = new User();
        view.showGreetings();
        view.showFirstname();
        newUser.setFirstName(in.nextLine());
        view.showLastname();
        newUser.setLastName(in.nextLine());
        view.getUsernameLabel();
        newUser.setUsername(in.nextLine());
        view.getPasswordLabel();
        newUser.setPassword(in.nextLine());
        if(register.registerUser(newUser)) {
            view.registerSuccess();
        } else {
            view.registerFailed();
        }
    }
}

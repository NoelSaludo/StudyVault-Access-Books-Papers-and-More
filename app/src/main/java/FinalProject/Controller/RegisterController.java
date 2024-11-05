package FinalProject.Controller;

import java.util.Scanner;

import FinalProject.Model.Register;
import FinalProject.Model.Data.User;
import FinalProject.View.RegisterView;

public class RegisterController {
    private Register register;
    private RegisterView view;
    Scanner in;

    public RegisterController(Register register, RegisterView view) {
        this.register = register;
        this.view = view;
        in = new Scanner(System.in);
    }

    public void Register() {
        User newUser = new User();
        view.showGreetings();
        view.showFirstname();
        newUser.setFirstName(in.nextLine());
        view.showLastname();
        newUser.setLastName(in.nextLine());
        view.showUsername();
        newUser.setUsername(in.nextLine());
        view.showPassword();
        newUser.setPassword(in.nextLine());
        register.registerUser(newUser);
    }
}

package FinalProject.Controller;

import java.util.Scanner;
import java.util.regex.Pattern;

import FinalProject.Model.Register;
import FinalProject.Model.Data.User;
import FinalProject.Utils.DataCollector;
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
        String[] labels = {
                view.getUsernameLabel(),
                view.getPasswordLabel(),
                view.getFirstNameLabel(),
                view.getLastNameLabel()
        };
        view.showGreetings();
        String[] data = new DataCollector().getData(labels, in);
        if (data == null) return;
        newUser.setFirstName(data[2]);
        newUser.setLastName(data[3]);
        newUser.setUsername(data[0]);
        newUser.setPassword(data[1]);
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])[a-zA-Z0-9!@#$%^&*()_+={}:;\"'<>,.?/\\~`|-]{8,15}$";
        if (!newUser.getPassword().matches(regex)) {
            view.registerFailed();
            return;
        }
        if (register.registerUser(newUser)) {
            view.registerSuccess();
        } else {
            view.registerFailed();
        }
    }
}

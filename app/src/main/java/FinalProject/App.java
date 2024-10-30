package FinalProject;

import java.util.Scanner;

import FinalProject.Controller.LoginController;
import FinalProject.DB.Database;
import FinalProject.Model.Login;
import FinalProject.View.LoginView;

public class App {
    String greet = """
            Welcome to Waste Management
            [1] Login
            [2] Register
            [3] Exit
            """;
    int choice;
    Scanner in = new Scanner(System.in);
    Login login;
    LoginView loginView;
    LoginController loginController;

    App() {

        // login variables
        try {
        login = new Login("jdbc:mysql://localhost:3306/testdb","FinalProject","FinalProject123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginView = new LoginView();
        loginController = new LoginController(login, loginView);

        System.out.println(greet);
        choice = in.nextInt();
 
        switch (choice) {
            case 1:
                // TODO Login
                break;
            case 2:
                //TODO Register
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) {
        new App();
    }
}

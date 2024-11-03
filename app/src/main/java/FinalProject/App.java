package FinalProject;

import java.util.Scanner;

import FinalProject.Controller.LoginController;
import FinalProject.DB.Database;
import FinalProject.Model.Login;
import FinalProject.Model.Data.User;
import FinalProject.View.LoginView;

public class App {
    String greet = """
            Welcome to School Material and Resource Center
            [1] Login
            [2] Register
            [3] Exit
            """;
    String url="jdbc:mysql://localhost:3306/testdb", user = "FinalProject", password = "FinalProject123";
    int choice;
    Scanner in = new Scanner(System.in);
    Login login;
    LoginView loginView;
    LoginController loginController;
    User currUser;
    Database db;

    App() {
        try {
        db = new Database(url,user,password);
        login = new Login(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginView = new LoginView();
        loginController = new LoginController(login, loginView);

        System.out.println(greet);
        choice = in.nextInt();
 
        switch (choice) {
            case 1:
                currUser = loginController.showLogin();
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

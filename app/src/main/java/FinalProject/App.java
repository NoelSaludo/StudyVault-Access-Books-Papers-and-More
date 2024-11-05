package FinalProject;

import java.util.Scanner;

import FinalProject.Controller.LoginController;
import FinalProject.Controller.RegisterController;
import FinalProject.DB.Database;
import FinalProject.Model.Login;
import FinalProject.Model.Register;
import FinalProject.Model.Data.User;
import FinalProject.View.LoginView;
import FinalProject.View.RegisterView;

public class App {
    String greet = """
            Welcome to StudyVault
            [1] Login
            [2] Register
            [3] Exit
            """;
    String url = "jdbc:mysql://localhost:3306/testdb", user = "FinalProject", password = "FinalProject123";
    Login login;
    LoginView loginView;
    LoginController loginController;
    Register reg;
    RegisterController regController;
    RegisterView regView;
    User currUser;
    Database db;

    App() {
        try {
            db = new Database(url, user, password);
            login = new Login(db);
            reg = new Register(db);

        } catch (Exception e) {
            e.printStackTrace();
        }
        loginView = new LoginView();
        loginController = new LoginController(login, loginView);

        regView = new RegisterView();
        regController = new RegisterController(reg, regView);

    }

    public void run() {
        Scanner in = new Scanner(System.in);
        while (currUser == null) {
            System.out.println(greet);
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    currUser = loginController.showLogin();
                    if (currUser != null) {
                        // TODO write the client
                        System.out.println("Login");
                    }
                    break;
                case 2:
                    regController.Register();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
        in.close();

    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

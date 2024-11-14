package FinalProject;

import java.util.Scanner;

import FinalProject.Controller.ClientController;
import FinalProject.Controller.LoginController;
import FinalProject.Controller.RegisterController;
import FinalProject.DB.Database;
import FinalProject.Model.Client;
import FinalProject.Model.Login;
import FinalProject.Model.Register;
import FinalProject.Model.Data.User;
import FinalProject.View.ClientView;
import FinalProject.View.LoginView;
import FinalProject.View.RegisterView;

public class App {
    String greet = """
            Welcome to StudyVault
            [1] Login
            [2] Register
            [3] Exit
            Enter choice: """;
    String url = "jdbc:mysql://localhost:3306/testdb", user = "FinalProject", password = "FinalProject123";
    Database db;

    // Login classes
    Login login;
    LoginView loginView;
    LoginController loginController;

    // Register classes
    Register reg;
    RegisterController regController;
    RegisterView regView;

    // Client classes
    Client client;
    ClientController clientController;
    ClientView clientView;

    // Application states
    User currUser;

    App() {
        try {
            db = new Database(url, user, password);
            login = new Login(db);
            reg = new Register(db);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        loginView = new LoginView();
        loginController = new LoginController(login, loginView);

        regView = new RegisterView();
        regController = new RegisterController(reg, regView);
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        while (currUser == null) {
            System.out.print(greet);
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    currUser = loginController.Login(in);
                    if (currUser != null) {
                        // TODO write the client
                        initClient();
                        clientController.run(in);
                    }
                    break;
                case 2:
                    regController.Register(in);
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
    private void initClient() {
        client = new Client(db,currUser);
        clientView = new ClientView();
        clientController = new ClientController(client, clientView);
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

package FinalProject;

import java.util.Scanner;

public class App {
    String greet = """
            Welcome to Waste Management
            [1] Login
            [2] Register
            [3] Exit
            """;
    int choice;
    Scanner in = new Scanner(System.in);

    App() {
        System.out.println(greet);
        choice = in.nextInt();

    }

    public static void main(String[] args) {
        new App();
    }
}

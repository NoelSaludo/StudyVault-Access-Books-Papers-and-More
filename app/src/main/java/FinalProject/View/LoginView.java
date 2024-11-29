package FinalProject.View;

public class LoginView {
    public String getUsernameLabel() {
        return "Username: ";
    }

    public String getPasswordLabel() {
        return "Password: ";
    }

    public void cancelOption() {
        System.out.println("Enter 0 to cancel login");
    }

    public void errorMessage() {
        System.out.println("Login error try again");
        System.out.println("User not found");
    }

    public void label(String x) {
        System.out.print(x);
    }
}

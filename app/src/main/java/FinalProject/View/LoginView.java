package FinalProject.View;

public class LoginView {
    public void showUsername() {
        System.out.print("Username: ");
    }
    public void showPassword() {
        System.out.print("Password: ");
    }
    public void cancelOption() {
        System.out.println("Enter 0 to cancel login");
    }
    public void errorMessage() {
        System.out.println("Login error try again");
        System.out.println("User not found");
    }
}

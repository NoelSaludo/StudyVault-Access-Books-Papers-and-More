package FinalProject.View;

public class RegisterView extends LoginView {
    String greetings;

    public void showGreetings() {
        System.out.println("Registering a new User");
    }

    public void showFirstname() {
        System.out.print("Firstname: ");
    }

    public void showLastname() {
        System.out.print("Lastname: ");
    }

    public void registerSuccess() {
        System.out.println("Registered Successfully");
    }

    public void registerFailed() {
        System.out.println("Failed to register");
    }

}

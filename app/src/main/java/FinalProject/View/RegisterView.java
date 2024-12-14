package FinalProject.View;

public class RegisterView extends LoginView {
    String greetings;

    public void showGreetings() {
        System.out.println("Registering a new User (press 0 to cancel)");
    }

    public String getFirstNameLabel() {
        return "Firstname: ";
    }

    public String getLastNameLabel() {
        return "Lastname: ";
    }

    public void registerSuccess() {
        System.out.println("Registered Successfully");
    }

    public void registerFailed() {
        System.out.println("Failed to register");
    }

    @Override
    public String getPasswordLabel() {
        return super.getPasswordLabel() +"\n(must contatin a Upper Case Letter, Lower Case Letter, and number and any of this symbols \n(!@#$%^&*()_+={}:;\"'<>,.?/\\~|-`)): ";
    }
}

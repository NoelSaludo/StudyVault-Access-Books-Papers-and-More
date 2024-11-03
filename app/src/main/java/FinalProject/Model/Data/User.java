package FinalProject.Model.Data;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private double balance;
    private String password;


    public User(String firstName, String lastName, String username, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String user) {
        this.firstName = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

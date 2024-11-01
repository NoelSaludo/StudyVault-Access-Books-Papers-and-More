package FinalProject.Model.Data;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String pass;
    private String username;

    public User(int id, String firstName, String lastName, String pass, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pass = pass;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String user) {
        this.firstName = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
}

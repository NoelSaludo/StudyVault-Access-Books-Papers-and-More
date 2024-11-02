package FinalProject.Model.Data;

public class Educator extends User {

    public Educator(double balance, String firstName, String lastName, String username) {
        super(balance, firstName, lastName, username);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Educator class";
    }

}

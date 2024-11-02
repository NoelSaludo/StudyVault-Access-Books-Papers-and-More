package FinalProject.Model.Data;

public class Learner extends User {

    public Learner(double balance, String firstName, String lastName, String username) {
        super(balance, firstName, lastName, username);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Learner class";
    }

}

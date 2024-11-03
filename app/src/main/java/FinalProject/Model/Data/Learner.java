package FinalProject.Model.Data;

/**
 * Extends from the User class. It represents the learner.
 * */
public class Learner extends User {
    private int year_level;
    private String interests;

    public Learner(double balance, String firstName, String lastName, String username, int year_level,
            String interests) {
        super(username, firstName, lastName, balance);
        this.year_level = year_level;
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "Learner class";
    }

    public int getYear_level() {
        return year_level;
    }


    public void setYear_level(int year_level) {
        this.year_level = year_level;
    }


    public String getInterests() {
        return interests;
    }


    public void setInterests(String interests) {
        this.interests = interests;
    }
}

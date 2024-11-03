package FinalProject.Model.Data;

public class Educator extends User {
    private int experience_years;
    private String expertise_area;
    private String institution;

    public Educator(String firstName, String lastName, String username, double balance, int experience_years,
            String expertise_area, String institution) {
        super(firstName, lastName, username, balance);
        this.experience_years = experience_years;
        this.expertise_area = expertise_area;
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Educator class";
    }

    public int getExperience_years() {
        return experience_years;
    }

    public void setExperience_years(int experience_years) {
        this.experience_years = experience_years;
    }

    public String getExpertise_area() {
        return expertise_area;
    }

    public void setExpertise_area(String expertise_area) {
        this.expertise_area = expertise_area;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}

package FinalProject.Model.Data;

import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<Material> favoriteMaterials;

    public User(int id, String firstName, String lastName, String username, String password, List<Material> favoriteMaterials) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.favoriteMaterials = favoriteMaterials;
    }

    public User() {
        //TODO Auto-generated constructor stub
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Material> getFavoriteMaterials() {
        return favoriteMaterials;
    }

    public void setFavoriteMaterials(List<Material> favoriteMaterials) {
        this.favoriteMaterials = favoriteMaterials;
    }
}

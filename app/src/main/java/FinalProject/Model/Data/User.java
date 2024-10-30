package FinalProject.Model.Data;

public class User {
    private int id;
    private String name;
    private String pass;

    public User(int id, String user, String pass) {
        this.id = id;
        this.name = user;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String user) {
        this.name = user;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}

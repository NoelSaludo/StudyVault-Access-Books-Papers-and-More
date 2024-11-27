package FinalProject.Model;

import java.sql.SQLException;

import FinalProject.DB.Database;
import FinalProject.Model.Data.User;

public class Login {
    Database db;
    private int id;
    private String username, password, firstName, lastName;

    public Login(Database db) {
        this.db = db;
    }

    /**
     * finds a valid user
     *
     * @return User*/
    public User findUser(String name, String password) {
        User user = null;
        try {
            int id = db.find("user_account", "username",name);
            user = db.findUser(id);
            if (user.getPassword().equals(password)){
                return user;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    /**
     * finds a valid admin
     *
     * @return User*/
    public User findAdmin(String name, String password) {
        try {
            int id = db.find("user_account", "username", name);
            User user = db.findAdmin(id);
            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

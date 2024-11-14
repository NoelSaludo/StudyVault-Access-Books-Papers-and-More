package FinalProject.Model;

import java.sql.SQLException;

import FinalProject.DB.Database;
import FinalProject.Model.Data.User;

public class Login {
    Database db;
    private int id;
    private String username, password, firstName, lastName;

    public Login(Database db) throws SQLException, ClassNotFoundException {
        this.db = db;
    }

    public User findUser(String name, String password) {
        User user = null;
        try {
            int id = db.find("user_account", "username",name);
            user = db.findUser(id);
            if (user.getPassword().equals(password) && user != null) {
                return user;
            } else {
                System.out.println("Incorrect Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexcpected Error in findUser");
        }
        return null;
    }
}

package FinalProject.Model;

import java.sql.ResultSet;
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
            ResultSet rs = db.findByName(name);
            this.id = rs.getInt("user_id");
            this.username = rs.getString("username");
            this.password = rs.getString("password");
            this.firstName = rs.getString("first_Name");
            this.lastName = rs.getString("last_name");
            if (this.password.equals(password)) {
                user = new User(this.id, this.firstName, this.lastName, this.username, this.password);
            } else {
                System.out.println("Incorrect Password");
            }
        } catch (SQLException e) {
            System.out.println("User not Found");
        } catch (Exception e) {
            System.out.println("Unexcpected Error in findUser");
        }
        return user;
    }
}

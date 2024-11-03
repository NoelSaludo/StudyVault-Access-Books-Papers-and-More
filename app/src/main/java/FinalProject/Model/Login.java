package FinalProject.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import FinalProject.DB.Database;
import FinalProject.Model.Data.Educator;
import FinalProject.Model.Data.Learner;
import FinalProject.Model.Data.User;

public class Login {
    private User currUser;
    Database db;

    public Login(Database db) throws SQLException, ClassNotFoundException {
        this.db = db;
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    public User findUser(String name, String password) {
        User user = null;
        try {
            ResultSet rs = db.findByName(name);
            Double balance = rs.getDouble("balance");
            String firstName = rs.getString("first_name"), lastName = rs.getString("last_name");
            if (rs.getString("password").equals(password)) {
                if (db.findInLearner(rs.getInt("id"))) {
                    user = new Learner(balance, firstName, lastName, name);
                } else {
                    user = new Educator(balance, firstName, lastName, name);
                }
            }
        } catch (SQLException e) {
            System.out.println("Username not found");
            e.printStackTrace();
        }

        return user;
    }
}

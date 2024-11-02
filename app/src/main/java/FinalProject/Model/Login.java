package FinalProject.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import FinalProject.DB.Database;
import FinalProject.Model.Data.Educator;
import FinalProject.Model.Data.Learner;
import FinalProject.Model.Data.User;

public class Login extends Database {
    private User currUser;

    public Login(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        super(url, user, pass);
        // TODO Auto-generated constructor stub
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    @Override
    public int insertUser(String name, String pass) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertUser'");
    }

    public User findUser(String name, String password) {
        User user = null;
        try {
            ResultSet rs = findByName(name);
            Double balance = rs.getDouble("balance");
            String firstName = rs.getString("first_name"), lastName = rs.getString("last_name");
            if (rs.getString("password").equals(password)) {
                if (findInLearner(rs.getInt("id"))) {
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

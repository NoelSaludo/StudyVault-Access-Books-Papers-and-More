package FinalProject.Model;

import java.sql.SQLException;
import java.sql.Statement;

import FinalProject.DB.Database;
import FinalProject.Model.Data.User;

public class Login extends Database {
    private User currUser;

    public Login(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        super(url, user, pass);
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    @Override
    public int insertUser(String name, String pass) {
        Statement stmt;
        int result = 0;
        try {
            stmt = getCon().createStatement();
            stmt.execute("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

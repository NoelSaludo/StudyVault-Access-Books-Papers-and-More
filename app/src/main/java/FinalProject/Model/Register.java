package FinalProject.Model;

import java.sql.SQLException;

import FinalProject.DB.Database;
import FinalProject.Model.Data.User;

public class Register {
    Database db;

    public Register(Database db) {
        this.db = db;
    }

    public Boolean registerUser(User user) {
        try {
            db.InsertUser(user);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

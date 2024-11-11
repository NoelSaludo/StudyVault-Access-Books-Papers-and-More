package FinalProject.Model;

import java.sql.SQLException;

import FinalProject.DB.Database;
import FinalProject.Model.Data.User;

public class Register {
    Database db;

    public Register(Database db) {
        this.db = db;
    }

    public void registerUser(User user) {
        try {
            if (db.InsertUser(user)) {
                System.out.println("User registered successfully");
            } else {
                System.out.println("User failed to register");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

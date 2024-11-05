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
            db.InsertUser(user);
        } catch (SQLException e) {
            System.out.println("Insert failed. Unexpected SQLException Error");
        }
        
    }
}

package FinalProject.Model;

import FinalProject.DB.Database;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends Client {

    public Admin(Database db) {
        super(db);
    }

    public Boolean deleteMaterial(int id) {
        try {
            db.deleteMaterial(id);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean updateMaterial(Scanner in) {
        return false;
    }
}

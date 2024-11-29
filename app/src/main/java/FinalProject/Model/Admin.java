package FinalProject.Model;

import FinalProject.DB.Database;
import FinalProject.Model.Data.Material;

import java.sql.SQLException;

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

    public Boolean updateMaterial(Material mat) {
        try{
            db.updateMaterial(
                    mat.getList(),
                    mat.toString()
            );
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

package FinalProject.Model;

import FinalProject.DB.Database;
import FinalProject.Model.Data.Material;
import FinalProject.Model.Data.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    Database db;
    User user;

    public Client(Database db, User user) {
        this.db = db;
        this.user = user;
    }

    public List<Material> findMaterials(String materialName) {
        List<Material> materials = new ArrayList<>();
        try {
            materials = db.findMaterial(materialName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materials;
    }

    public String getUsername() {
        return  user.getUsername();
    }
}

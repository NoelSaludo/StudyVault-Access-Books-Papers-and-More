package FinalProject.Model;

import FinalProject.DB.Database;
import FinalProject.Model.Data.Book;
import FinalProject.Model.Data.Material;
import FinalProject.Model.Data.Paper;
import FinalProject.Model.Data.User;

import java.sql.SQLException;
import java.sql.Date;
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
        return user.getUsername();
    }

    public void addBook(Book book) {
        try {
            db.addMaterial(book.getTitle(), book.getAuthor(), book.getLanguage(), book.getUrl(), new Date(book.getPublishedDate().getTime()));
            book.setId(db.find("material_table", "material_title", book.getTitle()));
            db.addBook(book.getId(), book.getISBN(), book.getPublisher());
            System.out.println("Book added");
        } catch (SQLException e) {
            System.out.println("Book was not added");
            System.out.println(e.getMessage());
        }
    }

    public void addPaper(Paper paper) {
        try {
            db.addMaterial(paper.getTitle(), paper.getAuthor(), paper.getLanguage(), paper.getUrl(), new Date(paper.getPublishedDate().getTime()));
            paper.setId(db.find("material_table", "material_title", paper.getTitle()));
            db.addPaper(paper.getId(), paper.getDOI(), paper.getJournalName());
            System.out.println("Paper added");
        } catch (SQLException e) {
            System.out.println("Paper was not added");
            System.out.println(e.getMessage());
        }

    }

    public void addFavorite(int id) {
        try {
            db.addFav(id, user.getId());
            System.out.println("Favorite added");
        } catch (SQLException e) {
            System.out.println("Favorite was not added");
            System.out.println(e.getMessage());
        }
    }

    public List<Material> getFavorites(int id) {
        List<Material> materials = new ArrayList<>();
        try {
            materials = db.getFav(id);
        } catch (SQLException e) {
            System.out.println("Error at getting favorites");
            System.out.println(e.getMessage());
        }
        return materials;
    }

    public int getID() {
        return user.getId();
    }
}

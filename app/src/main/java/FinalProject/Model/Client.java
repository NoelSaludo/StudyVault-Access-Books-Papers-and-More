package FinalProject.Model;

import FinalProject.DB.Database;
import FinalProject.Model.Data.*;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client {
    Database db;
    User user;

    private String[] MaterialAttributes = {
            "Title: ", // 0
            "Author: ", // 1
            "Language: ",// 2
            "URL: ",// 3
            "Published Date (dd/MM/yyyy): ",// 4
            "ISBN: ",// 5
            "Publisher: ",// 6
            "DOI: ",// 7
            "Journal Name: ",// 8
            "Platform: ",// 9
            "Duration (Minutes): ",// 10
            "Type (ACADEMIC, PROFFESSIONAL, WEBINAR): ",// 11
    };

    public Client(Database db) {
        this.db = db;
    }

    public List<Material> findMaterials(String materialName) {
        List<Material> materials = new ArrayList<>();
        try {
            materials = db.findMaterial(materialName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return materials;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Boolean addBook(Book book) {
        try {
            db.addMaterial(book.getTitle(), book.getAuthor(), book.getLanguage(), book.getUrl(), new Date(book.getPublishedDate().getTime()));
            book.setId(db.find("material_table", "material_title", book.getTitle()));
            db.addBook(book.getId(), book.getISBN(), book.getPublisher());
            return true;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean addPaper(Paper paper) {
        try {
            db.addMaterial(paper.getTitle(), paper.getAuthor(), paper.getLanguage(), paper.getUrl(), new Date(paper.getPublishedDate().getTime()));
            paper.setId(db.find("material_table", "material_title", paper.getTitle()));
            db.addPaper(paper.getId(), paper.getDOI(), paper.getJournalName());
            return true;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean addVideo(Video video) {
        try {
            db.addMaterial(video.getTitle(), video.getAuthor(), video.getLanguage(), video.getUrl(), new Date(video.getPublishedDate().getTime()));
            video.setId(db.find("material_table", "material_title", video.getTitle()));
            db.addVideo(video.getId(), video.getDuration(), video.getPlatform());
            return true;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean addSeminar(Seminar seminar) {
        try {
            db.addMaterial(seminar.getTitle(), seminar.getAuthor(), seminar.getLanguage(), seminar.getUrl(), new Date(seminar.getPublishedDate().getTime()));
            seminar.setId(db.find("material_table", "material_title", seminar.getTitle()));
            db.addSeminar(seminar.getId(), seminar.getType(), seminar.getDuration());
            return true;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
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

    public void setUser(User currUser) {
        user = currUser;
    }

    public Material getMaterial(int id) {
        try {
            return db.getMaterial(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String[] getMaterialAttributes() {
        return MaterialAttributes;
    }

    public void setMaterialAttributes(String[] materialAttributes) {
        MaterialAttributes = materialAttributes;
    }
}
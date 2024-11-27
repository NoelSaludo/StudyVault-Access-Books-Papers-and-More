package FinalProject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import FinalProject.Model.Data.*;
import FinalProject.Model.Enum.Type;

public class Database {
    private final Connection con;

    public Database(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection(url, user, pass);
    }

    /* ===== Public methods ===== */
    // Finding methods ------------
    /**
     * A simple find function that returns the id
     * @param table database table
     * @param column column where to search
     * @param name name of the item
     * @return id
     * */
    public int find(String table, String column, String name) throws SQLException {
        ResultSet rs;
        String query = "SELECT * FROM " + table + " WHERE " + column + " = ? ";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, name);
        rs = stm.executeQuery();
        if (!rs.next()) {
            return -1;
        }
        return rs.getInt("id");
    }

    /**
     * finds a user from the database using and id
     * @param id id of the user
     * @return User
     * */
    public User findUser(int id) throws SQLException {
        String query = "SELECT * FROM user_account WHERE id = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (!rs.next()) {
            System.out.println("No such user");
            return null;
        }

        return new User(id,
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("username"),
                rs.getString("password")
        );
    }

    /**
     * find materials that contains the substring from the material_table
     * @param name substring to search for
     * @return List of Material
     * */
    public List<Material> findMaterial(String name) throws SQLException {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM material_table WHERE material_title LIKE ?";
        PreparedStatement stm = con.prepareStatement(query);
        name = "%" + name + "%";
        stm.setString(1, name);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            materials.add(getMaterial(id));
        }
        return materials;
    }

    /**
     * deletes a material using the id
     * @param id material's id
     * */
    public void deleteMaterial(int id) throws SQLException {
        String query = "DELETE FROM material_table WHERE material_table.id = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.execute();
    }

    /**
     * finds an admin from the database
     * @param id user id of the admin
     * @return Admin
     * @throws SQLException if it did not find a user with the same id
     */
    public User findAdmin(int id) throws SQLException {
        String query = "SELECT * FROM user_account INNER JOIN admin ON user_account.id = admin.user_id WHERE user_account.id = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }
        return null;
    }

    // Get methods ------------

    /**
     * returns all the material with from the favorite table with the corresponding user id
     * @param id user id
     * @return List of Favorite material
     * @throws SQLException if the user id was not found
     */
    public List<Material> getFav(int id) throws SQLException {
        String query = """
                SELECT * FROM user_account
                INNER JOIN favorites ON favorites.user_id=user_account.id
                INNER JOIN material_table ON favorites.material_id=material_table.id
                WHERE user_account.id = ?""";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        List<Material> materials = new ArrayList<>();
        while (rs.next()) {
            materials.add(getMaterial(rs.getInt("material_id")));
        }
        return materials;
    }

    // Insert methods -------------

    /**
     * Inserts a user to the database
     *
     * @param newUser the new user to insert
     * @throws SQLException if the insertion failed
     */
    public void InsertUser(User newUser) throws SQLException {
        String query = """
                    INSERT INTO user_account(first_name, last_name, username, password) VALUES (?,?,?,?)
                """;
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, newUser.getFirstName());
        stm.setString(2, newUser.getLastName());
        stm.setString(3, newUser.getUsername());
        stm.setString(4, newUser.getPassword());
    }


    /**
     * Adds a material to the material_table
     * @param Title title of the material
     * @param Author author of the material
     * @param language language the material uses
     * @param url link of the material
     * @param publishedDate published date of the material
     * @throws SQLException if the insertion failed
     */
    public void addMaterial(String Title, String Author, String language, String url, Date publishedDate) throws SQLException {
        String query = "INSERT INTO material_table(material_title, material_author, material_language, material_url, material_published_date) VALUES (?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, Title);
        stm.setString(2, Author);
        stm.setString(3, language);
        stm.setString(4, url);
        stm.setDate(5, publishedDate);
        stm.execute();
    }

    /**
     * Adds a book to the book_table
     * @param id material_id of the book
     * @param isbn international standard book number
     * @param publisher publisher of the book
     * @throws SQLException if the insertion failed
     */
    public void addBook(int id, String isbn, String publisher) throws SQLException {
        String query = "INSERT INTO book_table(material_id,isbn,publisher) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setString(2, isbn);
        stm.setString(3, publisher);
        stm.execute();
    }

    /**
     * adds paper to the paper_table
     * @param id material_id of the paper
     * @param doi digital object identifier
     * @param journalName journal name where to paper is found
     * @throws SQLException if the insertion failed
     */
    public void addPaper(int id, String doi, String journalName) throws SQLException {
        String query = "INSERT INTO paper_table(material_id,doi,journal_name) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setString(2, doi);
        stm.setString(3, journalName);
        stm.execute();
    }

    /**
     * adds video to the video_table
     * @param id material_id of the video
     * @param duration duration of the video in minutes
     * @param resolution the resolution of the video
     * @throws SQLException if the insertion failed
     */
    public void addVideo(int id, int duration, String resolution) throws SQLException {
        String query = "INSERT INTO video_table(material_id,duration,resolution) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setInt(2, duration);
        stm.setString(3, resolution);
        stm.execute();
    }

    /**
     * adds a seminar to the seminar_table
     * @param id material_id of the seminar
     * @param type ACADEMIC, PROFESSIONAL, WEBINAR
     * @param duration duration of the seminar in hours
     * @throws SQLException if the insertion fail
     */
    public void addSeminar(int id, Type type, int duration) throws SQLException {
        String query = "INSERT INTO seminar_table(material_id,type,duration) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setString(2, type.toString());
        stm.setInt(3, duration);
        stm.execute();
    }

    /**
     * Add a favorite material using the user_id and material_id
     * @param id material_id
     * @param userID user_id
     * @throws SQLException if the insertion fail
     */
    public void addFav(int id, int userID) throws SQLException {
        String query = "INSERT INTO favorites(user_id, material_id) VALUES (?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, userID);
        stm.setInt(2, id);
        stm.execute();
    }

    /* ====== Private methods ====== */

    /**
     * determines which material to return
     * @param rs the result of a query
     * @return A material
     * @throws SQLException if there is a missing column
     */
    private Material whichMaterial(ResultSet rs) throws SQLException {
        Material material = null;
        if (rs.next()) {
            int mat_id = rs.getInt("id");
            String title = rs.getString("material_title"),
                    author = rs.getString("material_author"),
                    language = rs.getString("material_language"),
                    url = rs.getString("material_url");
            Date published_date = rs.getDate("material_published_date");

            if (rs.getInt("book_id") != 0) {
                material = new Book(
                        mat_id, title, author, language, url, published_date,
                        rs.getString("isbn"),
                        rs.getString("publisher")
                );
            } else if (rs.getInt("paper_id") != 0) {
                material = new Paper(
                        mat_id, title, author, language, url, published_date,
                        rs.getString("doi"),
                        rs.getString("journal_name")
                );
            } else if (rs.getInt("video_id") != 0) {
                material = new Video(
                        mat_id, title, author, language, url, published_date,
                        rs.getInt("duration"),
                        rs.getString("resolution")
                );
            } else if (rs.getInt("seminar_id") != 0) {
                material = new Seminar(
                        mat_id, title, author, language, url, published_date,
                        Type.valueOf(rs.getString("type")),
                        rs.getInt("duration")
                );
            }
        } else {
            return null;
        }
        return material;
    }

    /**
     * Returns the material found from all the table that corresponds with the material_table
     * @param id material_id
     * @return Material
     * @throws SQLException if there is no similar id or column is missing
     */
    private Material getMaterial(int id) throws SQLException {
        String query = """
                SELECT * FROM material_table
                LEFT JOIN book_table BT ON material_table.id = bt.material_id
                LEFT JOIN paper_table PT ON material_table.id = pt.material_id
                LEFT JOIN video_table VT ON material_table.id = vt.material_id
                LEFT JOIN seminar_table ST ON material_table.id = st.material_id
                WHERE material_table.id = ?;
                """;
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        return whichMaterial(rs);
    }

}

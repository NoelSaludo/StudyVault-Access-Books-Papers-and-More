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

    // Get methods ------------
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
     public Boolean InsertUser(User newUser) throws SQLException {
        String query = """
                    INSERT INTO user_account(first_name, last_name, username, password) VALUES (?,?,?,?)
                """;
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, newUser.getFirstName());
        stm.setString(2, newUser.getLastName());
        stm.setString(3, newUser.getUsername());
        stm.setString(4, newUser.getPassword());
        return stm.execute();
    }


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

     public void addBook(int id, String isbn, String publisher) throws SQLException {
        String query = "INSERT INTO book_table(material_id,isbn,publisher) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setString(2, isbn);
        stm.setString(3, publisher);
        stm.execute();
    }

     public void addPaper(int id, String doi, String journalName) throws SQLException {
        String query = "INSERT INTO paper_table(material_id,doi,journal_name) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setString(2, doi);
        stm.setString(3, journalName);
        stm.execute();
    }

     public void addVideo(int id, int duration, String resolution) throws SQLException {
        String query = "INSERT INTO video_table(material_id,duration,resolution) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setInt(2, duration);
        stm.setString(3, resolution);
        stm.execute();
    }

     public void addSeminar(int id, Type type, int duration) throws SQLException {
        String query = "INSERT INTO seminar_table(material_id,type,duration) VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.setString(2, type.toString());
        stm.setInt(3, duration);
        stm.execute();
    }

     public void addFav(int id, int userID) throws SQLException {
         String query = "INSERT INTO favorites(user_id, material_id) VALUES (?,?)";
         PreparedStatement stm = con.prepareStatement(query);
         stm.setInt(1, userID);
         stm.setInt(2, id);
         stm.execute();
     }

    /* ====== Private methods ====== */
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

     public void deleteMaterial(int id) throws SQLException {
         String query = "DELETE FROM material_table WHERE material_table.id = ?";
         PreparedStatement stm = con.prepareStatement(query);
         stm.setInt(1, id);
         stm.execute();
     }

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
 }

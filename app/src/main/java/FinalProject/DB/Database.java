package FinalProject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import FinalProject.Model.Data.Book;
import FinalProject.Model.Data.Material;
import FinalProject.Model.Data.Paper;
import FinalProject.Model.Data.User;

public class Database {
    private final Connection con;

    public Database(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection(url, user, pass);
    }

    /**
     * This function finds a user in the user account by SQL query to find the user
     * using the name
     *
     * @return ResultSet
     * @throws SQLException
     */
    public int findByName(String table, String column ,String name) throws SQLException {
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

        return new User(id, rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"));
    }

    /**
     * Insert a new User to the database
     *
     * @return Boolean
     * @throws SQLException
     */
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

    public void addMaterial(String Title, String Author, String language, String url, Date publishedDate) throws SQLException {
        String query = "INSERT INTO material_table(material_title, material_author, material_language, material_url, material_published_date) VALUES (?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, Title);
        stm.setString(2, Author);
        stm.setString(3,  language);
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

    // Private methods
    private Material whichMaterial(ResultSet rs) throws SQLException {
        Material material = null;
        if (rs.next()) {
            int mat_id = rs.getInt("id");
            String title = rs.getString("material_title"),
                    author = rs.getString("material_author"),
                    language = rs.getString("material_language"),
                    url = rs.getString("material_url");
            Date published_date = rs.getDate("material_published_date");

            if (rs.getInt("id") != 0) {
                material = new Book(
                        mat_id, title, author, language, url, published_date,
                        rs.getString("isbn"),
                        rs.getString("publisher")
                );
            } else if (rs.getInt("id") != 0) {
                material = new Paper(
                        mat_id, title, author, language, url, published_date,
                        rs.getString("doi"),
                        rs.getString("journal_name")
                );
            }
        }
        return material;
    }

    private Material getMaterial(int id) throws SQLException {
        String query = """
                SELECT * FROM material_table
                LEFT JOIN book_table BT ON material_table.id = bt.id
                LEFT JOIN paper_table PT ON material_table.id = pt.id
                WHERE material_table.id = ?;
                """;
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        return whichMaterial(rs);
    }

}

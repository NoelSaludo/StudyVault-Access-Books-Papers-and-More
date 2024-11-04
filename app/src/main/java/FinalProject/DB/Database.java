package FinalProject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import FinalProject.Model.Data.User;

public class Database {
    private Connection con;

    public Database(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection(url, user, pass);
    }

    /**
     * This function finds a user in the user account by SQL query to find the user
     * using the name
     * 
     * @throws SQLException
     * @return ResultSet
     */
    public ResultSet findByName(String name) throws SQLException {
        ResultSet rs;
        String query = "SELECT * FROM user_account WHERE username = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, name);
        rs = stm.executeQuery();
        rs.next();
        return rs;
    }

    public void InsertUser(User newUser) {
    }

    /**
     * @return Connection
     */
    public Connection getCon() {
        return con;
    }

}

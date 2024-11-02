package FinalProject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {
    private Connection con;

    public Database(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection(url, user, pass);
    }

    /**
     * inserts a User in the table
     * 
     * @param name, pass
     * @return int
     */
    public abstract int insertUser(String name, String pass);

    /**
     * This function finds a user in the user account by SQL query to find the user using the name
     * @throws SQLException
     * @return ResultSet
     * */
    public ResultSet findByName(String name) throws SQLException {
        ResultSet rs;
        String query = "SELECT * FROM user_account WHERE user_account.username = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, name);
        rs = stm.executeQuery();
        rs.next();
        return rs;
    }
     public Boolean findInLearner(int id) throws SQLException {
        Boolean res = null;
        String query = "SELECT * FROM user_account INNER JOIN learner ON user_account.id=learner.id WHERE user_account.id = ?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        res = stm.execute();
        return res;
    }

    /**
     * @return Connection
     */
    public Connection getCon() {
        return con;
    }

}

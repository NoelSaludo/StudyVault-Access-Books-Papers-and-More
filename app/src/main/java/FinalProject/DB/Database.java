package FinalProject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {
    private Connection con;

    public Database(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection(url, user, pass);
        Statement stmt = con.createStatement();
        String createUserTable = "";
        stmt.execute(createUserTable);
    }

    /**
     * inserts a User in the table
     * @param name, pass
     * @return int
     */
    public abstract int insertUser(String name, String pass);

    public Connection getCon() {
        return con;
    }

}

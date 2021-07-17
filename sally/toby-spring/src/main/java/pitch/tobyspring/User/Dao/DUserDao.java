package pitch.tobyspring.User.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao extends UserDao_Extends{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // N사의 DB 연결
        Class.forName("org.mariadb.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/toby","scott","1234");
        return c;
    }
}

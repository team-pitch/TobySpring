package pitch.tobyspring.User.Dao;

import pitch.tobyspring.User.Domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao_Class{
    private SimpleConnectionMaker simpleConnectionMaker;

    //한번만 만들어 인스턴스 변수에 저장해두고 메소드에서 사용하게 함
    public UserDao_Class(){
        simpleConnectionMaker = new SimpleConnectionMaker();
    }
    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection c = simpleConnectionMaker.makeNewConnection();

        PreparedStatement ps = c.prepareStatement("insert into user(id,name,password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection c = simpleConnectionMaker.makeNewConnection();

        PreparedStatement ps = c.prepareStatement("select * from user where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
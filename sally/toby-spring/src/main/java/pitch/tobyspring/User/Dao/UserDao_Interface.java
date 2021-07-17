package pitch.tobyspring.User.Dao;

import pitch.tobyspring.User.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao_Interface {
    //인터페이스를 통해 오브젝트에 접근
    private ConnectionMaker connectionMaker;

    public UserDao_Interface(){
        connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        //인터페이스에 정의된 메소드를 사용하므로 클래스가 바뀌어도 메소드 이름이 변경되지 않음
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("insert into user(id,name,password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();

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

package me.devsign.toby.dao;

import me.devsign.toby.connection.ConnectionMaker;
import me.devsign.toby.connection.DConnectionMaker;
import me.devsign.toby.connection.SimpleConnectionMaker;
import me.devsign.toby.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into user(id, name, password) values(?, ?, ?)"
        );

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from user where id = ?"
        );
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while(rs.next()) {
            user.setId(rs.getString(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
        }

        rs.close();
        ps.close();
        c.close();
        return user;
    }
}

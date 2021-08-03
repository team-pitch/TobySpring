package me.devsign.toby.dao;

import me.devsign.toby.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BadUserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)"
        );

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
    }

    public void delete(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
    }

    public User get() throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        return null;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost:1521/test", "sa", ""
        );

        return c;
    }
}

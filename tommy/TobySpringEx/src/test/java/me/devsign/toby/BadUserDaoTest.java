package me.devsign.toby;

import me.devsign.toby.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BadUserDaoTest {
    @BeforeEach
    public void before() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost:1521/test", "sa", ""
        );
        PreparedStatement ps = c.prepareStatement(
                "truncate table users"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    @Test
    public void add() throws ClassNotFoundException, SQLException {
        User user = new User("devsigner", "wonjune", "passowrd");
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost:1521/test", "sa", ""
        );
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
}
package me.devsign.toby.dao;

import me.devsign.toby.connection.ConnectionMaker;
import me.devsign.toby.connection.DConnectionMaker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DaoFactoryTest {
    @Autowired
    private static DataSource ds;
    private static DaoFactory daoFactory;

    static {
        daoFactory = new DaoFactory((ConnectionMaker) new DConnectionMaker(ds));
    }

    @Test
    void run() {
        UserDao userDao = daoFactory.userDao();
    }
}
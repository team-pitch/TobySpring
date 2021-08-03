package me.devsign.toby.dao;

import me.devsign.toby.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class UserDaoTest {
    @Autowired
    UserDao dao;

    @BeforeEach
    public void before() {

    }
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        User user = new User("aa", "wonjune", "1234");
        dao.add(user);
        User user2 = dao.get(user.getId());

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));
    }
}
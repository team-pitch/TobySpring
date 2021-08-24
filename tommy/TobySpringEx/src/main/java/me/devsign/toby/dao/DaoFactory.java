package me.devsign.toby.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    private DataSource dataSource;

    @Autowired
    public DaoFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setJdbcTemplate(this.dataSource);

        return userDao;
    }
}

package me.devsign.toby.dao;

import me.devsign.toby.connection.ConnectionMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    private ConnectionMaker connectionMaker;

    @Autowired
    public DaoFactory(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker);
    }
}

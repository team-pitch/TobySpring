package me.devsign.toby.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DConnectionMaker implements ConnectionMaker {
    protected final DataSource ds;

    @Autowired
    public DConnectionMaker(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Connection makeConnection() throws SQLException {
        return ds.getConnection();
    }
}

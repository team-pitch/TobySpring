package me.devsign.toby.dao;

import me.devsign.toby.connection.ConnectionMaker;
import me.devsign.toby.connection.DConnectionMaker;
import me.devsign.toby.connection.SimpleConnectionMaker;
import me.devsign.toby.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();

        user.setId(rs.getString(1));
        user.setName(rs.getString(2));
        user.setPassword(rs.getString(3));
        return user;
    };

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    public void add(final User user) throws SQLException {
        this.jdbcTemplate.update("insert into user(id, name, password) values(?, ?, ?)"
                , user.getId(), user.getName(), user.getPassword());
    }

    public int getCount() {
//        return this.jdbcTemplate.query(
//                con -> con.prepareStatement("select count(*) from users"),
//                rs -> {
//                    rs.next();
//                    return rs.getInt(1);
//                });
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public User get(String id) {
        return (User) this.jdbcTemplate.query("select * from users where id = ?",
                userRowMapper, id);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users", userRowMapper);
    }
}

package study.toby_spring.Ch1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import package toby_spring.Ch1.domain.User;

//getConnection() 메서드를 추출해서 중복을 제거
public class UserDao_method {
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();

		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}


	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
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

	// DB 연결 기능이 필요하면 getConnection() 메서드를 이용하게 한다
	// ClassNotFoundException : 중복된 코드르르 독립적인 메서드로 만들어서 중복을 제거
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "root",
				"admin123");
		return c;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();

		User user = new User();
		user.setId("kny");
		user.setName("kny");
		user.setPassword("1234");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " 조회 성공");
	}

}

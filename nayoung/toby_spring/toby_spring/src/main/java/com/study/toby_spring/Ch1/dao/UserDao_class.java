package study.toby_spring.Ch1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import toby_spring.Ch1.domain.User;

// 독립된 SimpleConnectionMaker를 사용하게 만든 UserDao
public abstract class UserDao_class{
	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDao_class() {
		this.simpleConnectionMaker = new SimpleConnectionMaker();  
		// 상태를 관리하는 것도 아니니 한 번만 만들어 인스턴스 변수에 저장해두고 메서드에서 사용하게 한다
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.simpleConnectionMaker.getConnection();

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
		Connection c = this.simpleConnectionMaker.getConnection();
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

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao_class dao = new NUserDao();

		User user = new User();
		user.setId("kny");
		user.setName("kny");
		user.setPassword("1234");

		dao.add(user);
			
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + "조회 성공");
	}

}

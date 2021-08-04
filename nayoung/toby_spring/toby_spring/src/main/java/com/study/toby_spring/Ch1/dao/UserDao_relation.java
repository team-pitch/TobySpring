package study.toby_spring.Ch1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import toby_spring.Ch1.domain.User;

public class UserDao_relation {
	private ConnectionMaker connectionMaker;  // 인터페이스를 통해 오브젝트에 접근하므로 구체적인 클래스 정보를 알 필요가 없음
	
//	public UserDao_relation() {
//		connectionMaker = new DConnectionMaker();
//	}

	// 수정된 생성자
	public UserDao_relation(ConnectionMaker simpleConnectionMaker) {
		this.connectionMaker = simpleConnectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		// 인터페이스에서 정의된 메서드 사용 -> 클래스 변경에도 메서드 이름이 변경될 일은 없음

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
		Connection c = this.connectionMaker.makeConnection();
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

	

}

package study.toby_spring.Ch1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// ConnectionMaker 구현 클래스
public class DConnectionMaker implements ConnectionMaker {

	// D사의 독자적 방법으로 Connection을 생성
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook?characterEncoding=UTF-8", "root", "admin123");
		return c;
	}
}

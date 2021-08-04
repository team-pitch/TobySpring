package study.toby_spring.Ch1.dao;

import java.sql.SQLException;

import toby_spring.Ch1.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConnectionMaker connectionMaker = new DConnectionMaker();  // UserDao_relation이 사용할 ConnectionMaker 구현 클래스를 결정하고 오브젝트 생성
		UserDao_relation dao = new UserDao_relation(connectionMaker);

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

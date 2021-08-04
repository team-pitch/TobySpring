package study.toby_spring.Ch1.dao;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import toby_spring.Ch1.domain.User;

public class UserDaoTest_context {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao_relation dao = context.getBean("userDao_relation", UserDao_relation.class);

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

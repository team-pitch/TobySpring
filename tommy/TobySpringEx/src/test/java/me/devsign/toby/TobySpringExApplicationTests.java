package me.devsign.toby;

import me.devsign.toby.dao.BadUserDao;
import me.devsign.toby.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class TobySpringExApplicationTests {
	@Test
	void main() throws SQLException, ClassNotFoundException {
		BadUserDao badUserDao = new BadUserDao();
		User user = new User();

		user.setId("test-01");
		user.setName("wonjune");
		user.setPassword("pwd01");

		badUserDao.add(user);
	}
}

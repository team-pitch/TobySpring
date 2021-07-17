package pitch.tobyspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pitch.tobyspring.User.Dao.UserDao;
import pitch.tobyspring.User.Domain.User;

import java.sql.SQLException;

@SpringBootTest
class UserDaoTest {

    @Test
    void userDaoTest() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();

        User user = new User();
        user.setId("sykwon");
        user.setName("권수연");
        user.setPassword("suyeon");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }

}

package pitch.tobyspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pitch.tobyspring.User.Dao.ConnectionMaker;
import pitch.tobyspring.User.Dao.DConnectionMaker;
import pitch.tobyspring.User.Dao.DaoFactory;
import pitch.tobyspring.User.Dao.UserDao_Relation;
import pitch.tobyspring.User.Domain.User;

import java.sql.SQLException;

@SpringBootTest
class UserDaoTest_Factory {

    @Test
    void userDaoTest() throws SQLException, ClassNotFoundException {
        UserDao_Relation userDao = new DaoFactory().userDao();

        User user = new User();
        user.setId("chjang");
        user.setName("장창훈");
        user.setPassword("changhoon");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }

}

package pitch.tobyspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pitch.tobyspring.User.Dao.ConnectionMaker;
import pitch.tobyspring.User.Dao.DConnectionMaker;
import pitch.tobyspring.User.Dao.DaoFactory;
import pitch.tobyspring.User.Dao.UserDao_Relation;
import pitch.tobyspring.User.Domain.User;

import java.sql.SQLException;

@SpringBootTest
class UserDaoTest_AppContext {

    @Test
    void userDaoTest() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao_Relation userDao = context.getBean("userDao",UserDao_Relation.class);

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

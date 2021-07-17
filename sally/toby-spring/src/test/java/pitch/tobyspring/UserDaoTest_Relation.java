package pitch.tobyspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pitch.tobyspring.User.Dao.ConnectionMaker;
import pitch.tobyspring.User.Dao.DConnectionMaker;
import pitch.tobyspring.User.Dao.UserDao;
import pitch.tobyspring.User.Dao.UserDao_Relation;
import pitch.tobyspring.User.Domain.User;

import java.sql.SQLException;

@SpringBootTest
class UserDaoTest_Relation {

    @Test
    void userDaoTest() throws SQLException, ClassNotFoundException {
        //UserDao가 사용할 ConnectinMaker 구현 클래스를 결정하고 오브젝트 생성
        ConnectionMaker connectionMaker = new DConnectionMaker();
        //사용할 ConnectionMaker 타입의 오브젝트 제공, 결국 두 오브젝트 사이의 의존관계 설정 효과
        UserDao_Relation userDao = new UserDao_Relation(connectionMaker);


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

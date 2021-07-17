package pitch.tobyspring.User.Dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory_Spring {
    @Bean
    public UserDao_Relation userDao(){
        return new UserDao_Relation(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        //분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성 코드
        return new DConnectionMaker();
    }
}

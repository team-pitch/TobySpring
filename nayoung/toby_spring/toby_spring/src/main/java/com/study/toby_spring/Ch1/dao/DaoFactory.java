package study.toby_spring.Ch1.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 빈 팩토리가 사용할 설정정보를 담은 DaoFactory 클래스
// @Configuration : 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
// @Bean : 오브젝트 생성을 담당하는 IoC용 메서드라는 표시
@Configuration
public class DaoFactory {
	@Bean
	public UserDao_relation userDao() {
		UserDao_relation dao = new UserDao_relation(connectionMaker());
		return dao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}

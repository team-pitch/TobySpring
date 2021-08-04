package study.toby_spring.Ch1.dao;

public class UserDaoFactory {
	public UserDao_relation userDao() {
		UserDao_relation dao = new UserDao_relation(connectionMaker());
		return dao;
	}

	public ConnectionMaker connectionMaker() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		return connectionMaker;
	}
}

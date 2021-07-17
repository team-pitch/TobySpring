package pitch.tobyspring.User.Dao;

public class DaoFactory {
    public UserDao_Relation userDao(){
        //팩토리의 메소드는 UserDao타입의 오브젝트를 어떻게 만들고, 어떻게 준비시킬지 결정한다.
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao_Relation userDao = new UserDao_Relation(connectionMaker);
        return userDao;
    }
}

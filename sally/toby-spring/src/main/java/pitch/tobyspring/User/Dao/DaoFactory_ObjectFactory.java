package pitch.tobyspring.User.Dao;

public class DaoFactory_ObjectFactory {
    public UserDao_Relation userDao(){
        return new UserDao_Relation(connectionMaker());
    }

    public ConnectionMaker connectionMaker(){
        //분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성 코드
        return new DConnectionMaker();
    }
}

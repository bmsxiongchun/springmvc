package dao.impl;

import dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import pojo.User;

import java.util.List;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

//    SqlSessionFactory sqlSessionFactory;
//
//    //注入sqlSessionFactory
//    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
//        this.sqlSessionFactory = sqlSessionFactory;
//    }

    @Override
    public User findUserById(int id) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();

        SqlSession sqlSession = this.getSqlSession();
//        User user = sqlSession.selectOne("mapper.UserMapper.findUserById", id);
        User user = sqlSession.selectOne("test.findUserById", id);
//        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findUserByName(String username) throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();

        SqlSession sqlSession = this.getSqlSession();
        List<User> list = sqlSession.selectList("mapper.UserMapper.findUserByName", "xiong");
        sqlSession.close();
        return list;
    }
}

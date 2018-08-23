import dao.UserDao;
import dao.impl.UserDaoImpl;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pojo.User;
import pojo.UserCustom;
import pojo.UserQueryVo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisFirst {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String resource = "SqlMapConfig.xml";

        //加载配置文件到输入流中
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void FindUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        User user = null;
//        try {
//            user = sqlSession.selectOne("test.findUserById", 1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }
//        System.out.println(user);

        //使用Mybatis的Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        try {
            User user = mapper.findUserById(2);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void findUserByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        try {
            List<User> userList = mapper.findUserByName("xiong");
            System.out.println(userList.get(0).getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        List<User> list = null;
//
//        try {
//            list = sqlSession.selectList("test.findUserByName", "xiong");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }

//        System.out.println(list.get(0).getUsername());
    }

    @Test
    public void insertUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("chen");
        user.setAddress("hubei");
        user.setBirthday(new Date());
        user.setSex("woman");
//        try {
//            sqlSession.insert("test.insertUser", user);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insertUser(user);
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        try {
//            sqlSession.delete("test.deleteUser", 9);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            mapper.deleteUser(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("xiongchun");
        user.setBirthday(new Date());
        user.setAddress("love");
        user.setSex("man");
        user.setId(8);

//        try {
//            sqlSession.update("test.updateUser", user);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            mapper.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);

        User user = userDao.findUserById(1);
        System.out.println(user);
    }

    @Test
    public void findUserList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setUsername("xiong");
        userQueryVo.setUserCustom(userCustom);

        List<User> user = mapper.findUserList(userQueryVo);
        sqlSession.close();

        System.out.println(user);
    }

    @Test
    public void findUserCount() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo userQueryVo = new UserQueryVo();

        UserCustom userCustom = new UserCustom();

        userCustom.setUsername("xiong");
        userQueryVo.setUserCustom(userCustom);
        int count = mapper.findUserCount(userQueryVo);
        sqlSession.close();
        System.out.println(count);
    }

    @Test
    public void findUserListResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();

        userCustom.setUsername("xiong");
        userQueryVo.setUserCustom(userCustom);
        List<User> userListResultMap = mapper.findUserListResultMap(userQueryVo);
        sqlSession.close();
        System.out.println(userListResultMap);
    }
}

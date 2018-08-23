import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

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
    public void testFindUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = null;
        try {
            user = sqlSession.selectOne("test.findUserById", 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        System.out.println(user);

    }

    @Test
    public void findUserByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = null;

        try {
            list = sqlSession.selectList("test.findUserByName", "xiong");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        System.out.println(list.get(0).getUsername());
    }

    @Test
    public void insertUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("chen");
        user.setAddress("hubei");
        user.setBirthday(new Date());
        user.setSex("woman");
        try {
            sqlSession.insert("test.insertUser", user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            sqlSession.delete("test.deleteUser", 9);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
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

        try {
            sqlSession.update("test.updateUser", user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void findById() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);

        User user = userDao.findUserById(1);
        System.out.println(user);
    }
}

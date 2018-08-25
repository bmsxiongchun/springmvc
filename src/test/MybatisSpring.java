import dao.UserDao;
import mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

public class MybatisSpring {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    //从spring容器总获取userDao的bean
    @Test
    public void findUserById() throws Exception {
//        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
//        User user = userDao.findUserById(1);
//        System.out.println(user);

        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(1);
        System.out.println(user);
    }

}

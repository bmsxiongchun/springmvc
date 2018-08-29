//import dao.UserDao;
//import dao.impl.UserDaoImpl;
//import mapper.OrdersMapperCustom;
//import mapper.UserMapper;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.Before;
//import org.junit.Test;
//import pojo.*;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class MybatisFirst {
//
//    private SqlSessionFactory sqlSessionFactory;
//
//    @Before
//    public void init() throws IOException {
//        String resource = "SqlMapConfig.xml";
//
//        //加载配置文件到输入流中
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//
//        //创建会话工厂
//        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//    }
//
//    @Test
//    public void FindUserById() {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
////        User user = null;
////        try {
////            user = sqlSession.selectOne("test.findUserById", 1);
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            sqlSession.close();
////        }
////        System.out.println(user);
//
//        //使用Mybatis的Mapper
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        try {
//            User user = mapper.findUserById(2);
//            System.out.println(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Test
//    public void findUserByName() {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        try {
//            List<User> userList = mapper.findUserByName("xiong");
//            System.out.println(userList.get(0).getUsername());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        List<User> list = null;
////
////        try {
////            list = sqlSession.selectList("test.findUserByName", "xiong");
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            sqlSession.close();
////        }
//
////        System.out.println(list.get(0).getUsername());
//    }
//
//    @Test
//    public void insertUser() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        User user = new User();
//        user.setUsername("chen");
//        user.setAddress("hubei");
//        user.setBirthday(new Date());
//        user.setSex("woman");
////        try {
////            sqlSession.insert("test.insertUser", user);
////            sqlSession.commit();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            sqlSession.close();
////        }
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.insertUser(user);
//    }
//
//    @Test
//    public void deleteUser() {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
////        try {
////            sqlSession.delete("test.deleteUser", 9);
////            sqlSession.commit();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            sqlSession.close();
////        }
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        try {
//            mapper.deleteUser(8);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void updateUser() {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        User user = new User();
//        user.setUsername("xiongchun");
//        user.setBirthday(new Date());
//        user.setAddress("love");
//        user.setSex("man");
//        user.setId(8);
//
////        try {
////            sqlSession.update("test.updateUser", user);
////            sqlSession.commit();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            sqlSession.close();
////        }
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        try {
//            mapper.updateUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void findById() throws Exception {
////        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
//        UserDao userDao = new UserDaoImpl();
//        User user = userDao.findUserById(1);
//        System.out.println(user);
//    }
//
//    @Test
//    public void findUserList() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        UserQueryVo userQueryVo = new UserQueryVo();
//        UserCustom userCustom = new UserCustom();
//        userCustom.setUsername("xiong");
//
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        userQueryVo.setIds(list);
//        userQueryVo.setUserCustom(userCustom);
//
//        List<User> user = mapper.findUserList(userQueryVo);
//        sqlSession.close();
//
//        System.out.println(user);
//    }
//
//    @Test
//    public void findUserCount() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        UserQueryVo userQueryVo = new UserQueryVo();
//
//        UserCustom userCustom = new UserCustom();
//
//        userCustom.setUsername("xiong");
//
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        userQueryVo.setIds(list);
//        userQueryVo.setUserCustom(userCustom);
//        int count = mapper.findUserCount(userQueryVo);
//        sqlSession.close();
//        System.out.println(count);
//    }
//
//    @Test
//    public void findUserListResultMap() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        UserQueryVo userQueryVo = new UserQueryVo();
//        UserCustom userCustom = new UserCustom();
//
//        userCustom.setUsername("xiong");
//        userQueryVo.setUserCustom(userCustom);
//        List<User> userListResultMap = mapper.findUserListResultMap(userQueryVo);
//        sqlSession.close();
//        System.out.println(userListResultMap);
//    }
//
//    @Test
//    public void findUserByList() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        UserQueryVo userQueryVo = new UserQueryVo();
//
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        userQueryVo.setIds(list);
//
//        UserCustom userCustom = new UserCustom();
//        userCustom.setUsername("xiong");
//        userCustom.setSex("female");
//        userQueryVo.setUserCustom(userCustom);
//        List<User> users = mapper.findUserList(userQueryVo);
//
//        sqlSession.close();
//
//        System.out.println(users);
//    }
//
//    @Test
//    public void findOrderUserList() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
//        List<OrderCustom> orderUserList = mapper.findOrderUserList();
//        sqlSession.close();
//        System.out.println(orderUserList);
//    }
//
//    @Test
//    public void findOrderUserListResultMap() throws Exception {
//
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
//
//        List<Orders> orderUserListResultMap = mapper.findOrderUserListResultMap();
//        sqlSession.close();
//        System.out.println(orderUserListResultMap);
//    }
//
//    @Test
//    public void findOrderAndOrderDetails() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
//        List<Orders> orderAndOrderDetails = mapper.findOrderAndOrderDetails();
//        sqlSession.close();
//        System.out.println(orderAndOrderDetails);
//
//    }
//
////    @Test
////    public void findOrderUserListLazyLoading() throws Exception {
////        SqlSession sqlSession = sqlSessionFactory.openSession();
////
////        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
////        List<Orders> orderUserListLazyLoading = mapper.findOrderUserListLazyLoading();
////        User user = orderUserListLazyLoading.get(0).getUser();
////        sqlSession.close();
////        System.out.println(user);
////    }
//
//
//    @Test
//    public void cacheTest() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        //第一次查询id为1的用户信息
//        User user1 = mapper.findUserById(1);
//        System.out.println(user1);
//
//        //加入更新的操作
////        user1.setSex("female");
////        user1.setAddress("hubeisheng");
////        mapper.updateUser(user1);
//
//        //第二次查询id为1的用户信息
//        User user2 = mapper.findUserById(1);
//        System.out.println(user2);
//
//        sqlSession.close();
//    }
//
//    @Test
//    public void cacheTest2() throws Exception {
//        SqlSession sqlSession1 = sqlSessionFactory.openSession();
//        SqlSession sqlSession2 = sqlSessionFactory.openSession();
//        SqlSession sqlSession3 = sqlSessionFactory.openSession();
//
//        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
//        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
//        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
//
//        //第一次查询用户id为1的用户
//        User user = mapper1.findUserById(1);
//        System.out.println(user);
//        sqlSession1.close();
//
//        // 中间修改用户清空缓存，目的防止脏数据
//        user.setUsername("hello");
//        mapper3.updateUser(user);
//        sqlSession3.commit();
//        sqlSession3.close();
//
//        // 第二次查询用户id为1的用户
//        User user1 = mapper2.findUserById(1);
//        System.out.println(user1);
//        sqlSession2.close();
//    }
//}
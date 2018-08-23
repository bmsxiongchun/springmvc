package mapper;

import pojo.User;
import pojo.UserQueryVo;

import java.util.List;

public interface UserMapper {

    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据name查询用户信息
    public List<User> findUserByName(String username) throws Exception;

    //根据
    public void insertUser(User user) throws Exception;

    public void deleteUser(int id) throws Exception;

    public void updateUser(User user) throws Exception;

    public List<User> findUserList(UserQueryVo userQueryVo) throws Exception;

    public int findUserCount(UserQueryVo userQueryVo) throws Exception;

    public List<User> findUserListResultMap(UserQueryVo userQueryVo) throws Exception;
}

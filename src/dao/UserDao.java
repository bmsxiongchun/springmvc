package dao;

import pojo.User;

import java.util.List;

public interface UserDao {

    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据name查询用户信息
    public List<User> findUserByName(String username) throws Exception;
}

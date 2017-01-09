package com.power.spring.lesson3.dao;


import com.power.spring.lesson3.model.User;

import java.util.List;

/**
 * Created by shenli on 2017/1/1.
 */
public interface UserDao {

    boolean createUser(User user);

    boolean deleteUser(long userId);

    boolean disableUser(long userId);

    List<User> queryUser(String userNamePrex, boolean onlyValidUser);

    User loadUserByNamePasswd(String userName, String md5Passed);

    boolean hasSeamUserName(String userName);

    List<User> queryAll();
}

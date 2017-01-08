package com.power.spring.service.impl;

import com.power.spring.dao.UserDao;
import com.power.spring.lession3.model.User;
import com.power.spring.lession3.model.UserSession;
import com.power.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenli on 2017/1/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public boolean disableUser(long userId) {
        return userDao.disableUser(userId);
    }

    @Override
    public List<User> queryUsers(String userNamePrex, boolean onlyValidUser) {
        return null;
    }

    @Override
    public UserSession login(String userName, String md5EncodedPassword) {
        return null;
    }
}

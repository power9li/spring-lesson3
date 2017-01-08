package com.power.spring.lesson3.service.impl;

import com.power.spring.lesson3.dao.UserDao;
import com.power.spring.lesson3.model.User;
import com.power.spring.lesson3.model.UserSession;
import com.power.spring.lesson3.service.UserService;
import com.power.spring.lesson3.utils.UserSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        return userDao.queryUser(userNamePrex,onlyValidUser);
    }

    @Override
    public UserSession login(String userName, String md5EncodedPassword) {
        User u = userDao.loadUserByNamePasswd(userName, md5EncodedPassword);
        UserSession us = new UserSession();

        if (u != null) {
            if (u.isEnabled()) {
                us.setUserName(u.getUserName());
                us.setUserId(u.getUserId());
                us.setValidSeconds(UserSessionUtils.VALID_TIME);
                us.setCreateTime(System.currentTimeMillis());
                us.setSessionId(UUID.randomUUID().toString());
                UserSessionUtils.addUserSession(us);
            }
        }
        return us;
    }
}

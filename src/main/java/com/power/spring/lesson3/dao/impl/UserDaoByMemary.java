package com.power.spring.lesson3.dao.impl;

import com.power.spring.lesson3.dao.UserDao;
import com.power.spring.lesson3.model.User;
import com.power.spring.lesson3.model.UserSession;
import com.power.spring.lesson3.utils.UserSessionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by shenli on 2017/1/9.
 */
@Component
public class UserDaoByMemary implements UserDao {


    private Map<Long,User> users = new HashMap<>();
    private AtomicLong maxId = new AtomicLong(1);

    @Override
    public boolean createUser(User user) {
        if (users.containsKey(user.getUserId()) || user.getUserId() != 0) {
            return false;
        }
        user.setUserId(maxId.incrementAndGet());
        users.put(user.getUserId(), user);
        System.out.println("save memary user = " + user);
        System.out.println("users.size() = " + users.size());
        return true;
    }

    @Override
    public boolean deleteUser(long userId) {
        return users.remove(userId) != null;
    }

    @Override
    public boolean disableUser(long userId) {
        User u = users.get(userId);
        if(u ==null)
            return false;
        else{
            u.setEnabled(false);
            return true;
        }
    }

    @Override
    public List<User> queryUser(String userNamePrex, boolean onlyValidUser) {
        List<User> subList = new ArrayList<>();
        for(User u : users.values()){
            if (u.getUserName().startsWith(userNamePrex)) {
                UserSession us = UserSessionUtils.getSessionByUserId(u.getUserId());
                if (us != null && us.isValid()) {
                    subList.add(u);
                }
            }
        }
        return subList;
    }

    @Override
    public User loadUserByNamePasswd(String userName, String md5Passed) {
        for (User u : users.values()) {
            if (u.getUserName().equals(userName) && u.getPassword().equals(md5Passed)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean hasSeamUserName(String userName) {
        for (User u : users.values()) {
            if(u.getUserName().equals(userName)){
                return false;
            }
        }
        return true;
    }
}

package com.power.spring.lesson3.dao.impl;

import com.power.spring.lesson3.dao.UserDao;
import com.power.spring.lesson3.model.User;
import com.power.spring.lesson3.model.UserSession;
import com.power.spring.lesson3.utils.UserSessionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by shenli on 2017/1/9.
 */
public class UserDaoByMemory implements UserDao {

    private Map<Long,User> users = new HashMap<>();
    private AtomicLong maxId = new AtomicLong(1);

    public UserDaoByMemory(List<User> userList){
        for (User u : userList) {
            Long uid = maxId.incrementAndGet();
            u.setUserId(uid);
            users.put(uid, u);
        }
    }

    @Override
    public boolean createUser(User user) {
        if (users.containsKey(user.getUserId()) || user.getUserId() != 0) {
            return false;
        }
        user.setUserId(maxId.incrementAndGet());
        users.put(user.getUserId(), user);
        System.out.println("save memory user = " + user);
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
        System.out.println("UserDaoByMemory.queryUser");
        System.out.println("users.size() = " + users.size());
        List<User> subList = new ArrayList<>();
        for(User u : users.values()){
            System.out.println("u.getUserName() = " + u.getUserName());
            if (u.getUserName().startsWith(userNamePrex)) {
                if(onlyValidUser) {
                    UserSession us = UserSessionUtils.getSessionByUserId(u.getUserId());
                    if (us != null && us.isValid()) {
                        subList.add(u);
                    }
                }
                else{
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

    @Override
    public List<User> queryAll() {
        List<User> list = new ArrayList<>();
        Collection<User> values = users.values();
        list.addAll(values);
        return list;
    }
}

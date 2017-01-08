package com.power.spring.lesson3.controller;

import com.power.spring.lession3.model.User;
import com.power.spring.lession3.model.UserSession;
import com.power.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shenli on 2017/1/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("create")
    @ResponseBody
    public Map<String,Object> create(User user){
        Map<String, Object> map = new HashMap<>();
        boolean rst = userService.createUser(user);
        map.put("success", rst);
        return map;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(Long userId){
        Map<String, Object> map = new HashMap<>();
        boolean rst = userService.deleteUser(userId);
        map.put("success", rst);
        return map;
    }

    @RequestMapping("disable")
    @ResponseBody
    public Map<String,Object> disable(Long userId){
        Map<String, Object> map = new HashMap<>();
        boolean rst = userService.disableUser(userId);
        map.put("success", rst);
        return map;
    }

    @RequestMapping("queryUsers")
    @ResponseBody
    public Map<String,Object> queryUsers(String userNamePrex, boolean onlyValidUser){
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.queryUsers(userNamePrex, onlyValidUser);
        map.put("success", true);
        map.put("users", users);
        return map;
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(String userName, String md5EncodedPassword){
        Map<String, Object> map = new HashMap<>();
        try {
            UserSession us = userService.login(userName, md5EncodedPassword);
            if (us != null) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        }catch(Exception e){
            map.put("success", false);
            map.put("reason", e.getMessage());
        }
        return map;
    }

}

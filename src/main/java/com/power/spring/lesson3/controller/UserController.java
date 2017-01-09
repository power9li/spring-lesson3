package com.power.spring.lesson3.controller;

import com.power.spring.lesson3.model.User;
import com.power.spring.lesson3.model.UserSession;
import com.power.spring.lesson3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    public UserController(){
        System.out.println("UserController.create()");
    }

    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    @ResponseBody
    public Map<String,Object> index(){
        System.out.println("UserController.index");
        Map<String, Object> map = new HashMap<>();
        map.put("welcome", "user.index");
        return map;
    }

    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody User user){
        System.out.println("UserController.create");
//        System.out.println("json = " + json);
        Map<String, Object> map = new HashMap<>();
//        User user = new UserConverter().convert(json);
        boolean rst = userService.createUser(user);
        map.put("success", rst);
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody Long userId){
        System.out.println("UserController.delete("+userId+")");
        Map<String, Object> map = new HashMap<>();
        boolean rst = userService.deleteUser(userId);
        map.put("success", rst);
        return map;
    }

    @RequestMapping("/disable")
    @ResponseBody
    public Map<String,Object> disable(@RequestBody Long userId){
        System.out.println("UserController.disable");
        Map<String, Object> map = new HashMap<>();
        boolean rst = userService.disableUser(userId);
        map.put("success", rst);
        return map;
    }

    @RequestMapping("/queryUsers")
    @ResponseBody
    public Map<String,Object> queryUsers(@RequestBody Map<String,Object> params){
        System.out.println(params.keySet().toString());
        String userNamePrex = (String)params.get("userNamePrex");
        boolean onlyValidUser = (Boolean) params.get("onlyValidUser");
        System.out.println("UserController.queryUsers(userNamePrex="+userNamePrex+",onlyValidUser="+onlyValidUser+")");
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.queryUsers(params);
        map.put("success", true);
        map.put("users", users);
        return map;
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(){
        System.out.println("UserController.queryAll");
        return new HashMap(){{
            put("users", userService.queryAll());
        }};
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String userName, String md5EncodedPassword){
        System.out.println("UserController.login");
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

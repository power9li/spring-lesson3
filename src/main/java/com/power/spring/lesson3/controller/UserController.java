package com.power.spring.lesson3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenli on 2017/1/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("create")
    @ResponseBody
    public Map<String,Object> create(){
        Map<String, Object> map = new HashMap<>();

        return map;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(){
        Map<String, Object> map = new HashMap<>();

        return map;
    }

    @RequestMapping("disable")
    @ResponseBody
    public Map<String,Object> disable(){
        Map<String, Object> map = new HashMap<>();

        return map;
    }

    @RequestMapping("queryUsers")
    @ResponseBody
    public Map<String,Object> queryUsers(){
        Map<String, Object> map = new HashMap<>();

        return map;
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(){
        Map<String, Object> map = new HashMap<>();

        return map;
    }

}

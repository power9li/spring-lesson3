package com.power.spring.lesson3.converter;

import com.google.gson.Gson;
import com.power.spring.lesson3.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by shenli on 2017/1/9.
 */
@Component
public class UserConverter implements Converter<String,User> {

    public UserConverter(){
        System.out.println("UserConverter.create()");
    }

    @Override
    public User convert(String source) {
        System.out.println("UserConverter.convert");
        System.out.println("source = " + source);
        User user = new Gson().fromJson(source, User.class);
        System.out.println("after convert user = " + user);
        return user;
    }
}

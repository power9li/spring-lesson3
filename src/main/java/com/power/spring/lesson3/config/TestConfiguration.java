package com.power.spring.lesson3.config;

import com.power.spring.lesson3.dao.UserDao;
import com.power.spring.lesson3.dao.impl.UserDaoByMemory;
import com.power.spring.lesson3.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shenli on 2017/1/8.
 */
@Configuration
@Profile("power.test")
@PropertySource("classpath:test.properties")
public class TestConfiguration {

    @Bean
    public UserDao userDao(){
        List<User> userList = Arrays.asList(new User[]{
                new User("zhangsan","passzhangsan123",true),
                new User("lisi","passlisi123",true),
                new User("wangwu","passwangwu123",true),
                new User("zhaoliu","pwzhaoliu456",false)
        });
        return new UserDaoByMemory(userList);
    }
}

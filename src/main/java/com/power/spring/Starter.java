package com.power.spring;

import com.power.spring.config.MyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by shenli on 2017/1/8.
 */
public class Starter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyConfiguration.class);
        context.refresh();
        context.registerShutdownHook();

        System.out.println("Server started ");

    }
}

package com.power.spring.lesson3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by shenli on 2017/1/8.
 */
@Configuration
@Profile("power.test")
@PropertySource("classpath:test.properties")
public class TestConfiguration {
}

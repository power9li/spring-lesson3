package com.power.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by shenli on 2017/1/8.
 */
@Configuration
@Import({ProdConfigurtion.class,TestConfiguration.class})
@ComponentScan("com.power.spring")
@EnableWebMvc
public class MyConfiguration {
}

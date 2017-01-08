package com.power.spring.lesson3.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by shenli on 2017/1/9.
 */
@EnableWebMvc
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("WebMvcConfig.configureMessageConverters");
//        converters.add(new Jaxb2RootElementHttpMessageConverter());
//        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(new StringHttpMessageConverter());
        converters.add(new GsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}

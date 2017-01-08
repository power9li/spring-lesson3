package com.power.spring.lesson3.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by shenli on 2017/1/8.
 */
public class Lesson3Initializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("Lesson3Initializer.onStartup");

        System.setProperty("spring.profiles.default","power.test");
        System.out.println("System.getProperty(\"spring.profiles.default\") = " + System.getProperty("spring.profiles.default"));
        System.out.println("System.getProperty(\"spring.profiles.active\") = " + System.getProperty("spring.profiles.active"));

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyConfiguration.class);

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

    }
}

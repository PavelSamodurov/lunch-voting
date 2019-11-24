package com.lunchvoting;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext()){
            applicationContext.getEnvironment();
            applicationContext.load("spring/spring-db.xml");
            applicationContext.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));
        }
    }
}

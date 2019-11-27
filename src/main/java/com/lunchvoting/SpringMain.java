package com.lunchvoting;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.repository.LunchMenuRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;

public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext()){
            applicationContext.getEnvironment();
            applicationContext.load("spring/spring-db.xml");
            applicationContext.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));

            LunchMenuRepository lunchMenuRepository = applicationContext.getBean(LunchMenuRepository.class);

            LunchMenu newLunchMenu = new LunchMenu(3,LocalDate.now());
            lunchMenuRepository.save(newLunchMenu);
            List<LunchMenu> all = lunchMenuRepository.getAll();
            System.out.println("Count" + all.size());
            for (LunchMenu lunchMenu : all){
                System.out.println(lunchMenu);
            }

        }
    }
}

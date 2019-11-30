package com.lunchvoting;

import com.lunchvoting.model.User;
import com.lunchvoting.repository.LunchMenuRepository;
import com.lunchvoting.repository.UserRepository;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext()){
            applicationContext.getEnvironment();
            applicationContext.load("spring/spring-db.xml");
            applicationContext.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));

            LunchMenuRepository lunchMenuRepository = applicationContext.getBean(LunchMenuRepository.class);
            UserRepository userRepository = applicationContext.getBean(UserRepository.class);

            List<User> allUsers = userRepository.getAll();

            for (User user: allUsers){
                System.out.println(user.toString());
            }

//            LunchMenu newLunchMenu = new LunchMenu(3,LocalDate.now());
//            lunchMenuRepository.save(newLunchMenu);
//            List<LunchMenu> all = lunchMenuRepository.getAll();
//            System.out.println("Count" + all.size());
//            for (LunchMenu lunchMenu : all){
//                System.out.println(lunchMenu);
//            }

        }
    }
}

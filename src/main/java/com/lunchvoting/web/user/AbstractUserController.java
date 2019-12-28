package com.lunchvoting.web.user;

import com.lunchvoting.model.User;
import com.lunchvoting.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        log.info("get all");
        return userRepository.findAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return userRepository.findById(id).get();
    }
}

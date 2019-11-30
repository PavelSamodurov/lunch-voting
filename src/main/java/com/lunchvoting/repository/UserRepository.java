package com.lunchvoting.repository;

import com.lunchvoting.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private CrudUserRepository crudRepository;

    public List<User> getAll(){
        return crudRepository.getAll();
    }
}
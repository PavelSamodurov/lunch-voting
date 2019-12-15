package com.lunchvoting.repository;

import com.lunchvoting.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private CrudUserRepository crudUserRepository;

    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    public Optional<User> findById(int id) {
        return crudUserRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return crudUserRepository.findByEmail(email);
    }

    public List<User> findAll(){
        return crudUserRepository.findAll();
    }
}
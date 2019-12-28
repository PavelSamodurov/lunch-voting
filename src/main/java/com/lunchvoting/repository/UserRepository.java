package com.lunchvoting.repository;

import com.lunchvoting.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");
    @Autowired
    private CrudUserRepository crudUserRepository;

    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    public Optional<User> getById(int id) {
        return crudUserRepository.getById(id);
    }

    public Optional<User> getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    public List<User> getAll(){
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }
}
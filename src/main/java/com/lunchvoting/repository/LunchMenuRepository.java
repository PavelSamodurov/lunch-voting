package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class LunchMenuRepository {
    @Autowired
    CrudLunchMenuRepository crudLunchMenuRepository;

    public boolean delete(int id) {
        return crudLunchMenuRepository.deleteById(id) != 0;
    }

    public List<LunchMenu> getAll() {
        return crudLunchMenuRepository.getAll();
    }

    @Transactional
    public LunchMenu save(LunchMenu lunchMenu){
        return crudLunchMenuRepository.save(lunchMenu);
    }
}

package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public class LunchMenuRepository {
    @Autowired
    private CrudLunchMenuRepository crudLunchMenuRepository;

    public Optional<LunchMenu> get(int id) {
        return crudLunchMenuRepository.getById(id);
    }

    public boolean delete(int id) {
        return crudLunchMenuRepository.deleteById(id) != 0;
    }

    @Transactional
    public LunchMenu save(LunchMenu lunchMenu){
        return crudLunchMenuRepository.save(lunchMenu);
    }

    public Optional<LunchMenu> getByRestaurantAndDate(Restaurant restaurant, LocalDate date){
        return crudLunchMenuRepository.getByRestaurantAndDate(restaurant, date);
    }

    public List<LunchMenu> getAllByRestaurant(Restaurant restaurant){
        return crudLunchMenuRepository.getAllByRestaurant(restaurant);
    }

    public List<LunchMenu> getAllByDate(LocalDate date) {
        return crudLunchMenuRepository.getAllByDate(date);
    }
}

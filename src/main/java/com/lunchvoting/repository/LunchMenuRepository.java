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

    public boolean delete(int id) {
        return crudLunchMenuRepository.deleteById(id) != 0;
    }

    @Transactional
    public LunchMenu save(LunchMenu lunchMenu, Restaurant restaurant){
        lunchMenu.setRestaurant(restaurant);
        return crudLunchMenuRepository.save(lunchMenu);
    }

    public Optional<LunchMenu> findByRestaurantAndDate(Restaurant restaurant, LocalDate date){
        return crudLunchMenuRepository.findByRestaurantAndDate(restaurant, date);
    }

    public List<LunchMenu> findAllByDate(LocalDate date) {
        return crudLunchMenuRepository.findAllByDate(date);
    }
}

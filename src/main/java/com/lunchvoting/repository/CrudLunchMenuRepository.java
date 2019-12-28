package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudLunchMenuRepository extends JpaRepository<LunchMenu, Integer> {
    @Modifying
    @Transactional
    int deleteById(@Param("id") int id);

    List<LunchMenu> getAllByDate(LocalDate date);

    Optional<LunchMenu> getByRestaurantAndDate(Restaurant restaurant, LocalDate date);
}

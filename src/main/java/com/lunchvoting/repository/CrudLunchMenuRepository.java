package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudLunchMenuRepository extends JpaRepository<LunchMenu, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM LunchMenu lm WHERE lm.id=:id")
    int deleteById(@Param("id") int id);

    List<LunchMenu> getAllByDate(LocalDate date);

    List<LunchMenu> getAllByRestaurant_Id(int restaurantId);

    Optional<LunchMenu> getByRestaurant_IdAndDate(int restaurantId, LocalDate date);

    Optional<LunchMenu> getById(int id);
}

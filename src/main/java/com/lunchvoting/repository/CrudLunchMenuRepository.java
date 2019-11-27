package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudLunchMenuRepository extends JpaRepository<LunchMenu, Integer> {
    @Modifying
    @Transactional
    int deleteById(@Param("id") int id);

    @Query("SELECT lm FROM LunchMenu lm")
    List<LunchMenu> getAll();

    @Modifying
    @Transactional
    public LunchMenu save(LunchMenu lunchMenu);

}

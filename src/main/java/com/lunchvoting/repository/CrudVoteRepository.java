package com.lunchvoting.repository;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.model.User;
import com.lunchvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    Optional<Vote> getByUser_IdAndLunchMenu_Date(int userId, LocalDate date);

    List<Vote> getAllByLunchMenu_RestaurantAndLunchMenu_Date(Restaurant restaurant, LocalDate date);
}
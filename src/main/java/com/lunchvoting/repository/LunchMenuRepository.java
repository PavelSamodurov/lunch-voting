package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LunchMenuRepository {
    @PersistenceContext
    private EntityManager em;

    public LunchMenu delete(LunchMenu lunchMenu){
        em.persist(lunchMenu);
        return lunchMenu;
    }
}

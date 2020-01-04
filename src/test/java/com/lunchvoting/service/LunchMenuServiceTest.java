package com.lunchvoting.service;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.LunchMenuTestData.*;
import static com.lunchvoting.RestaurantTestData.RESTAURANT1;
import static org.junit.jupiter.api.Assertions.*;

class LunchMenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected LunchMenuService lunchMenuService;

    @Test
    void delete() {
        lunchMenuService.delete(LUNCH_MENU1_ID);
        assertThrows(NotFoundException.class, () ->
                lunchMenuService.get(LUNCH_MENU1_ID));
    }

    @Test
    void create() {
        LunchMenu newLunchMenu = getNew();
        LunchMenu created = lunchMenuService.create(LocalDate.now().minusDays(2), RESTAURANT1);
        Integer newId = created.getId();
        newLunchMenu.setId(newId);
        LUNCH_MENU_MATCHERS.assertMatch(created, newLunchMenu);
        LUNCH_MENU_MATCHERS.assertMatch(lunchMenuService.get(newId), newLunchMenu);
    }

    @Test
    void get() throws Exception {
        LunchMenu actual = lunchMenuService.get(LUNCH_MENU1_ID);
        LUNCH_MENU_MATCHERS.assertMatch(actual, LUNCH_MENU1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                lunchMenuService.get(1));
    }

    @Test
    void getByRestaurantAndDate() {
        LunchMenu lunchMenu = lunchMenuService.getByRestaurantAndDate(LocalDate.now(), RESTAURANT1);
        LUNCH_MENU_MATCHERS.assertMatch(lunchMenu, LUNCH_MENU1);
    }

    @Test
    void getAllByRestaurant() {
        List<LunchMenu> lunchMenuList = lunchMenuService.getAllByRestaurant(RESTAURANT1);
        LUNCH_MENU_MATCHERS.assertMatch(lunchMenuList, LUNCH_MENU_LIST_OF_RESTAURANT1);
    }

    @Test
    void getAllByDate() {
        List<LunchMenu> lunchMenuList = lunchMenuService.getAllByDate(LocalDate.now());
        LUNCH_MENU_MATCHERS.assertMatch(lunchMenuList, LUNCH_MENU_LIST_ON_TODAY);
    }
}
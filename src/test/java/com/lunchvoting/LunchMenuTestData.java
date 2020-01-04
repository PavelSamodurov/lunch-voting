package com.lunchvoting;

import com.lunchvoting.model.LunchMenu;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.RestaurantTestData.*;
import static com.lunchvoting.model.AbstractBaseEntity.START_SEQ;

public class LunchMenuTestData {
    public static final int LUNCH_MENU1_ID = START_SEQ + 7;

    public static final LocalDate today = LocalDate.now();
    public static final LocalDate yesterday = today.minusDays(1);

    public static final LunchMenu LUNCH_MENU1 = new LunchMenu(LUNCH_MENU1_ID, today, RESTAURANT1);
    public static final LunchMenu LUNCH_MENU2 = new LunchMenu(LUNCH_MENU1_ID + 1, today, RESTAURANT2);
    public static final LunchMenu LUNCH_MENU3 = new LunchMenu(LUNCH_MENU1_ID + 2, today, RESTAURANT3);
    public static final LunchMenu LUNCH_MENU4 = new LunchMenu(LUNCH_MENU1_ID + 3, today, RESTAURANT4);
    public static final LunchMenu LUNCH_MENU5 = new LunchMenu(LUNCH_MENU1_ID + 4, today, RESTAURANT5);

    public static final LunchMenu LUNCH_MENU6 = new LunchMenu(LUNCH_MENU1_ID + 5, yesterday, RESTAURANT1);
    public static final LunchMenu LUNCH_MENU7 = new LunchMenu(LUNCH_MENU1_ID + 6, yesterday, RESTAURANT2);
    public static final LunchMenu LUNCH_MENU8 = new LunchMenu(LUNCH_MENU1_ID + 7, yesterday, RESTAURANT3);
    public static final LunchMenu LUNCH_MENU9 = new LunchMenu(LUNCH_MENU1_ID + 8, yesterday, RESTAURANT4);
    public static final LunchMenu LUNCH_MENU10 = new LunchMenu(LUNCH_MENU1_ID + 9, yesterday, RESTAURANT5);

    public static final List<LunchMenu> LUNCH_MENU_LIST_OF_RESTAURANT1 = List.of(LUNCH_MENU1, LUNCH_MENU6);

    public static final List<LunchMenu> LUNCH_MENU_LIST_ON_TODAY = List.of(LUNCH_MENU1, LUNCH_MENU2, LUNCH_MENU3, LUNCH_MENU4, LUNCH_MENU5);

    public static LunchMenu getNew() {
        return new LunchMenu(LocalDate.now().minusDays(2), RESTAURANT1);
    }

    public static TestMatchers<LunchMenu> LUNCH_MENU_MATCHERS = TestMatchers.useFieldsComparator(LunchMenu.class, "dishes");
}

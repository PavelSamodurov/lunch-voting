package com.lunchvoting;

import com.lunchvoting.model.Dish;

import java.util.Set;

import static com.lunchvoting.LunchMenuTestData.*;
import static com.lunchvoting.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final int DISH1_ID = START_SEQ + 17;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Закуска «Джумбус»", 890L, LUNCH_MENU1);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Суп из ягнёнка", 340L, LUNCH_MENU1);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Панированный сыр  ", 380L, LUNCH_MENU1);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Ассорти брускетт", 880L, LUNCH_MENU2);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Салат с ростбифом", 1290L, LUNCH_MENU2);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Розовая Джоли (Намибия)", 197L, LUNCH_MENU2);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "Устрица с сочным манго и соусом табаско", 430L, LUNCH_MENU3);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Буйабес из рыбы и морегадов", 490L, LUNCH_MENU3);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, "Утка с малиной", 150L, LUNCH_MENU4);
    public static final Dish DISH10 = new Dish(DISH1_ID + 9, "Клаб-сендвич с курицей и беконом", 510L, LUNCH_MENU4);
    public static final Dish DISH11 = new Dish(DISH1_ID + 10, "Аджапсандали", 450L, LUNCH_MENU5);
    public static final Dish DISH12 = new Dish(DISH1_ID + 11, "Салат по-мегрельски", 490L, LUNCH_MENU5);
    public static final Dish DISH13 = new Dish(DISH1_ID + 12, "Аласка чорба", 370L, LUNCH_MENU6);
    public static final Dish DISH14 = new Dish(DISH1_ID + 13, "Сибас «на граделе»", 350L, LUNCH_MENU6);
    public static final Dish DISH15 = new Dish(DISH1_ID + 14, "Ассорти мясных деликатесов", 980L, LUNCH_MENU7);
    public static final Dish DISH16 = new Dish(DISH1_ID + 15, "Салат понаш с уткой и фуа гра", 1290L, LUNCH_MENU7);
    public static final Dish DISH17 = new Dish(DISH1_ID + 16, "Вонголе в томатном соусе", 880L, LUNCH_MENU7);
    public static final Dish DISH18 = new Dish(DISH1_ID + 17, "Касабланка (Марокко)", 210L, LUNCH_MENU8);
    public static final Dish DISH19 = new Dish(DISH1_ID + 18, "Руккола с авокадо и аргентинскими креветками", 730L, LUNCH_MENU8);
    public static final Dish DISH20 = new Dish(DISH1_ID + 19, "Лосось крем-чиз", 160L, LUNCH_MENU9);
    public static final Dish DISH21 = new Dish(DISH1_ID + 20, "Клаб-сендвич с лососем", 490L, LUNCH_MENU9);
    public static final Dish DISH22 = new Dish(DISH1_ID + 21, "Грибной крем-суп", 390L, LUNCH_MENU9);
    public static final Dish DISH23 = new Dish(DISH1_ID + 22, "Сациви с курицей", 470L, LUNCH_MENU10);

    public static final Set<Dish> LUNCH_MENU1_DISHES = Set.of(DISH1, DISH2, DISH3);

    public static Dish getNew() {
        return new Dish(null, "Новая еда", 100L, LUNCH_MENU1);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Обновленная еда", 900L, LUNCH_MENU1);
    }

    public static TestMatchers<Dish> DISH_MATCHERS = TestMatchers.useFieldsComparator(Dish.class);
}

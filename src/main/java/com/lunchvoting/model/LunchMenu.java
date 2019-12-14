package com.lunchvoting.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "lunch_menu")
public class LunchMenu extends AbstractBaseEntity {
    private LocalDate date;

    public LunchMenu() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToMany
    Set<Dish> dishes;

    public LunchMenu(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
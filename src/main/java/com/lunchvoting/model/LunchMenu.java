package com.lunchvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "lunch_menu")
public class LunchMenu extends AbstractBaseEntity {

    private LocalDate date;

    public LunchMenu() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lunchMenu")
    @BatchSize(size = 200)
    Set<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lunchMenu")
    @JsonIgnore
    Set<Vote> votes;

    public LunchMenu(Integer id, LocalDate date, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
    }

    public LunchMenu(LocalDate date, Restaurant restaurant) {
        this(null, date, restaurant);
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
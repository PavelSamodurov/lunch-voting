package com.lunchvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lunchmenu_id", nullable = false)
    @JsonIgnore
    LunchMenu lunchMenu;

    public Dish() {
    }

    public Dish(Integer id, String name, Long price, LunchMenu lunchMenu) {
        super(id, name);
        this.price = price;
        this.lunchMenu = lunchMenu;
    }

    public Dish(String name, Long price, LunchMenu lunchMenu) {
        this(null, name, price, lunchMenu);
    }

    public LunchMenu getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(LunchMenu lunchMenu) {
        this.lunchMenu = lunchMenu;
    }
}
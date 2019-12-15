package com.lunchvoting.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {
    @ManyToOne
    User user;

    @ManyToOne
    LunchMenu lunchMenu;

    public Vote() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLunchMenu(LunchMenu lunchMenu) {
        this.lunchMenu = lunchMenu;
    }
}

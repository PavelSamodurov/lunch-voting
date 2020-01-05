package com.lunchvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {
    @ManyToOne
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    LunchMenu lunchMenu;

    public Vote() {
    }

    public Vote(Integer id, User user, LunchMenu lunchMenu) {
        super(id);
        this.user = user;
        this.lunchMenu = lunchMenu;
    }

    public Vote(User user, LunchMenu lunchMenu) {
        this(null, user, lunchMenu);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLunchMenu(LunchMenu lunchMenu) {
        this.lunchMenu = lunchMenu;
    }
}

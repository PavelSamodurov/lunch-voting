package com.lunchvoting.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    protected List<LunchMenu> lunchMenuList;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
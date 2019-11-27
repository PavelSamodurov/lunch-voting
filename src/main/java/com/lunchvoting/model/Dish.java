package com.lunchvoting.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {
    private Long price;
}
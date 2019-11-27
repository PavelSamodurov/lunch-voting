package com.lunchvoting.web;

import com.lunchvoting.repository.LunchMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LunchMenuController {

    @Autowired
    private LunchMenuRepository lunchMenuRepository;
}

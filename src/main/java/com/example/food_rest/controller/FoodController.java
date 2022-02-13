package com.example.food_rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class FoodController {

    public String createFood() {
        //http://localhost:8080/api/foods
        return null;
    }

    @RequestMapping("test")
    public String test () {
        return "it works!";
    }
}

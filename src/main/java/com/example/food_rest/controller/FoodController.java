package com.example.food_rest.controller;

import com.example.food_rest.dto.FoodDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public final class FoodController {

    @PostMapping("foods")
    public FoodDTO createFood(@RequestBody final FoodDTO foodDTO) {
        //http://localhost:8080/api/foods

        return foodDTO;
    }

    @GetMapping("test")
    public String test() {
        return "it works!";
    }
}

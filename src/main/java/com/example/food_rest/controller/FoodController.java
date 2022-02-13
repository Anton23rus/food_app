package com.example.food_rest.controller;

import com.example.food_rest.dto.FoodDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class FoodController {

    @PostMapping("foods")
    public FoodDTO createFood(@RequestBody FoodDTO foodDTO) {
        //http://localhost:8080/api/foods

        return foodDTO;
    }

    @GetMapping("test")
    public String test () {
        return "it works!";
    }
}

package com.example.food_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class FoodRestApplication {

    @SuppressWarnings("checkstyle:FinalParameters")
    public static void main(String[] args) {
        SpringApplication.run(FoodRestApplication.class, args);
    }
}

package com.example.food_rest.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class FoodEntity {
    private Long id;
    private String name;

    public FoodEntity() {
    }

    public FoodEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}

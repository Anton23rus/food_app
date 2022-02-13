package com.example.food_rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {

    @JsonProperty("foodname")
    private String name;

}

package com.example.food_rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class FoodDTO {

    @JsonProperty("foodname")
    @NonNull
    private String name;

}

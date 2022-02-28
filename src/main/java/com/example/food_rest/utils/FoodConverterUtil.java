package com.example.food_rest.utils;

import com.example.food_rest.entity.FoodEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class FoodConverterUtil {
    public List<FoodEntity> convertToEntityFromString(List<String> text, char delimiter) {
        return text.stream()
                .map(row -> row.split(String.valueOf(delimiter)))
                .map(arr -> new FoodEntity(Long.valueOf(arr[0]), arr[1]))
                .collect(Collectors.toList());
    }
}

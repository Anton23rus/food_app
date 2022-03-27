package com.example.food_rest.service;

import com.example.food_rest.dto.FoodDTO;
import com.example.food_rest.entity.FoodEntity;

import java.util.List;

public interface FoodService {
    void create(FoodDTO foodDTO);
    void update(FoodDTO foodDTO, Long id);
    void deleteById(Long id);
    void deleteByName(String name);
    void deleteAll();
    FoodEntity findById(Long id);
    List<FoodEntity> findByName(String name);
    List<FoodEntity> findAll(int offset);
}

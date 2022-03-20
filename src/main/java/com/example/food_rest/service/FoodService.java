package com.example.food_rest.service;

import com.example.food_rest.entity.FoodEntity;

import java.util.List;

public interface FoodService {
    boolean create(FoodEntity foodEntity);
    boolean update(FoodEntity foodEntity);
    boolean deleteById(Long id);
    boolean deleteByName(String name);
    boolean deleteAll();
    FoodEntity findById(Long id);
    List<FoodEntity> findByName(String name);
    List<FoodEntity> findAll();
}

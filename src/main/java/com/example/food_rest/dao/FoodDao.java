package com.example.food_rest.dao;

import com.example.food_rest.entity.FoodEntity;

import java.util.List;

public interface FoodDao {
    void create(FoodEntity foodEntity);
    void update(FoodEntity foodEntity);
    void deleteById(Long id);
    void deleteByName(String name);
    void deleteAll();
    FoodEntity findById(Long id);
    List<FoodEntity> findByName(String name);
    List<FoodEntity> findAll();
}

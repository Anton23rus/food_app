package com.example.food_rest.dao;

import com.example.food_rest.entity.FoodEntity;
import com.example.food_rest.utils.CsvWorkerUtil;
import com.example.food_rest.utils.FoodConverterUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodDaoImpl implements FoodDao {
    final private static String RESOURCE_FILE = "/Users/antonbook/Java/food_rest/src/main/resources/data/Food.csv";

    @Override
    public void create(FoodEntity foodEntity) {

    }

    @Override
    public void update(FoodEntity foodEntity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public FoodEntity findById(Long id) {
        return null;
    }

    @Override
    public List<FoodEntity> findByName(String name) {
        return null;
    }

    @Override
    public List<FoodEntity> findAll() {
        List<String> entityRows = CsvWorkerUtil.readCSVFile(RESOURCE_FILE, true);
        return FoodConverterUtil.convertToEntityFromString(entityRows, ',');
    }

    public static void main(String[] args) {
        FoodDaoImpl foodDaoImpl = new FoodDaoImpl();
        System.out.println(foodDaoImpl.findAll());
    }
}

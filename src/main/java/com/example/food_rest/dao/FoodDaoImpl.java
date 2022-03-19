package com.example.food_rest.dao;

import com.example.food_rest.entity.FoodEntity;
import com.example.food_rest.utils.CsvWorkerUtil;
import com.example.food_rest.utils.FoodConverterUtil;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FoodDaoImpl implements FoodDao {
    final private static String RESOURCE_FILE = "/Users/antonbook/Java/food_rest/src/main/resources/data/Food.csv";
    final private static Character CSV_DELIMETR = ',';

    private static FoodEntity deserializationFoodEntity(String textFoodEntity) {
        Long foodId = Long.valueOf(textFoodEntity.substring(0, textFoodEntity.indexOf(CSV_DELIMETR)));
        String foodName = textFoodEntity.substring(textFoodEntity.indexOf(CSV_DELIMETR) + 1);
        return FoodEntity.builder()
                .id(foodId)
                .name(foodName)
                .build();
    }

    private static List<FoodEntity> getFoodEntitiesFromCsv() {
        return CsvWorkerUtil.readCSVFile(RESOURCE_FILE, true).stream()
                .map(FoodDaoImpl::deserializationFoodEntity)
                .collect(Collectors.toList());
    }

    private static String serializationFoodEntity(FoodEntity foodEntity) {
        return String.format("%d%c%s", foodEntity.getId(), CSV_DELIMETR, foodEntity.getName());
    }

    private static void saveFoodEntitiesToCsv(List<FoodEntity> foodEntities) {
        List<String> textFoodEntities = foodEntities.stream()
                .map(e -> serializationFoodEntity(e))
                .collect(Collectors.toList());
        CsvWorkerUtil.writeToCsv(RESOURCE_FILE, textFoodEntities, false);
    }

    private static void appendFoodEntityToCsv(FoodEntity foodEntity) {
        CsvWorkerUtil.writeToCsv(RESOURCE_FILE, Collections.singletonList(serializationFoodEntity(foodEntity)),
                true);
    }

    @Override
    public void create(FoodEntity foodEntity) {
        boolean isExist = getFoodEntitiesFromCsv().stream()
                .anyMatch(e -> e.getId().equals(foodEntity.getId()));
        if (!isExist) {
            appendFoodEntityToCsv(foodEntity);
        } else {
            throw new RuntimeException(String.format("FoodEntity with id %d is already exists", foodEntity.getId()));
        }
    }

    @Override
    public void update(FoodEntity foodEntity) {
        List<FoodEntity> foodEntities = getFoodEntitiesFromCsv().stream()
                .peek(e -> {
                    if(e.getId().equals(foodEntity.getId())) {
                        e.setName(foodEntity.getName());
                    }})
                .collect(Collectors.toList());
        saveFoodEntitiesToCsv(foodEntities);
    }

    @Override
    public void deleteById(Long id) {
        List<FoodEntity> foodEntities = getFoodEntitiesFromCsv().stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
        saveFoodEntitiesToCsv(foodEntities);
    }

    @Override
    public void deleteByName(String name) {
        List<FoodEntity> foodEntities = getFoodEntitiesFromCsv().stream()
                .filter(e -> !e.getName().equals(name))
                .collect(Collectors.toList());
        saveFoodEntitiesToCsv(foodEntities);
    }

    @Override
    public FoodEntity findById(Long id) {
        return getFoodEntitiesFromCsv().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("foodEntity by id: %d is not found", id)));
    }

    @Override
    public List<FoodEntity> findByName(String name) {
        return getFoodEntitiesFromCsv().stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<FoodEntity> findAll() {
        return getFoodEntitiesFromCsv();
    }

    public static void main(String[] args) {
        FoodDaoImpl foodDaoImpl = new FoodDaoImpl();
        foodDaoImpl.deleteByName("orange");
//        System.out.println(foodDaoImpl.findAll());
//        System.out.println(foodDaoImpl.findByName("orange"));
//        System.out.println(foodDaoImpl.findById(1L));
    }
}

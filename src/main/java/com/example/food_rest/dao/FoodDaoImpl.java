package com.example.food_rest.dao;

import com.example.food_rest.entity.FoodEntity;
import com.example.food_rest.exceptions.DataBaseNotAvailableException;
import com.example.food_rest.utils.CsvWorkerUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class FoodDaoImpl implements FoodDao {
    //  final private String RESOURCE_FILE = "/Users/Miaton/Documents/Project/FoodApp/src/main/resources/data/Food.csv";
    final private String RESOURCE_FILE = "/Users/antonbook/Java/food_rest/src/main/resources/data/Food.csv";
    final private Character CSV_DELIMITER = ',';
    private Long currentFoodId;

    {
        List<FoodEntity> foodList = this.getFoodEntitiesFromCsv();
        if (foodList.size() != 0) {
            this.currentFoodId = foodList.get(foodList.size() - 1).getId();
        } else {
            this.currentFoodId = 1L;
        }
    }

    private FoodEntity deserializationFoodEntity(String textFoodEntity) {
        Long foodId = Long.valueOf(textFoodEntity.substring(0, textFoodEntity.indexOf(this.CSV_DELIMITER)));
        String foodName = textFoodEntity.substring(textFoodEntity.indexOf(CSV_DELIMITER) + 1);
        return FoodEntity.builder()
                .id(foodId)
                .name(foodName)
                .build();
    }

    private List<FoodEntity> getFoodEntitiesFromCsv() {
        return CsvWorkerUtil.readCSVFile(RESOURCE_FILE, true).stream()
                .map(e -> this.deserializationFoodEntity(e))
                .collect(Collectors.toList());
    }

    private String serializationFoodEntity(FoodEntity foodEntity) {
        return String.format("%d%c%s", foodEntity.getId(), CSV_DELIMITER, foodEntity.getName());
    }

    private void saveFoodEntitiesToCsv(List<FoodEntity> foodEntities) {
        List<String> textFoodEntities = foodEntities.stream()
                .map(e -> serializationFoodEntity(e))
                .collect(Collectors.toList());

        try {
            CsvWorkerUtil.writeToCsv(RESOURCE_FILE, textFoodEntities, false);
        } catch (IOException e) {
            throw new DataBaseNotAvailableException(RESOURCE_FILE);
        }
    }

    private void appendFoodEntityToCsv(FoodEntity foodEntity) {
        try {
            CsvWorkerUtil.writeToCsv(RESOURCE_FILE, Collections.singletonList(serializationFoodEntity(foodEntity)),
                    true);
        } catch (IOException e) {
            throw new DataBaseNotAvailableException(RESOURCE_FILE);
        }
    }

    @Override
    public void create(FoodEntity foodEntity) {
        boolean isExist = foodEntity.getId() != 0 && foodEntity.getId() <= currentFoodId;
        if (!isExist) {
            foodEntity.setId(++currentFoodId);
                appendFoodEntityToCsv(foodEntity);
        } else {
            throw new RuntimeException(String.format("FoodDao create operation: ERROR" +
                    "FoodEntity with id %d is already exists", foodEntity.getId()));
        }
    }

    @Override
    public void update(FoodEntity foodEntity) {
        List<FoodEntity> foodEntities = getFoodEntitiesFromCsv();
        boolean hasFoodEntityInCsv = foodEntities.stream()
                .anyMatch(e -> e.getId().equals(foodEntity.getId()));
        if (!hasFoodEntityInCsv) {
            throw new RuntimeException(String.format("FoodDao update operation: " +
                    "ERROR FoodEntity with id %d is not exists", foodEntity.getId()));
        }
        List<FoodEntity> updatedFoodEntities = getFoodEntitiesFromCsv().stream()
                .peek(e -> {
                    if (e.getId().equals(foodEntity.getId())) {
                        e.setName(foodEntity.getName());
                    }
                })
                .collect(Collectors.toList());
            saveFoodEntitiesToCsv(updatedFoodEntities);
    }

    @Override
    public void deleteById(Long id) {
        List<FoodEntity> foodEntities = getFoodEntitiesFromCsv().stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
        saveFoodEntitiesToCsv(foodEntities);
    }

    @Override
    public void deleteAll() {
        saveFoodEntitiesToCsv(Collections.emptyList());
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

    public void main(String[] args) {
        FoodDaoImpl foodDaoImpl = new FoodDaoImpl();
        foodDaoImpl.deleteByName("orange");
//        System.out.println(foodDaoImpl.findAll());
//        System.out.println(foodDaoImpl.findByName("orange"));
//        System.out.println(foodDaoImpl.findById(1L));
    }
}

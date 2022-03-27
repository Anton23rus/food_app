package com.example.food_rest.service;

import com.example.food_rest.dao.FoodDao;
import com.example.food_rest.dao.FoodDaoImpl;
import com.example.food_rest.dto.FoodDTO;
import com.example.food_rest.entity.FoodEntity;
import com.example.food_rest.utils.FoodConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    FoodDao foodDao;
    int portionSize = 10;

    @Autowired
    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public void create(FoodDTO foodDTO) {
        FoodEntity foodEntity = FoodConverterUtil.convertFoodDtoToFoodEntity(foodDTO);
        foodDao.create(foodEntity);
    }

    @Override
    public void update(FoodDTO foodDTO, Long id) {
        FoodEntity foodEntity = FoodConverterUtil.convertFoodDtoToFoodEntity(foodDTO);
        foodEntity.setId(id);
        foodDao.update(foodEntity);
    }

    @Override
    public void deleteById(Long id) {
        foodDao.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        foodDao.deleteByName(name);
    }

    @Override
    public void deleteAll() {
        foodDao.deleteAll();
    }

    @Override
    public FoodEntity findById(Long id) {
        return foodDao.findById(id);
    }

    @Override
    public List<FoodEntity> findByName(String name) {
        return foodDao.findByName(name);
    }

    @Override
    public List<FoodEntity> findAll(int offset) {
        List<FoodEntity> entityList = foodDao.findAll();
        return entityList.stream()
                .skip(offset)
                .limit(portionSize)
                .collect(Collectors.toList());
    }
}

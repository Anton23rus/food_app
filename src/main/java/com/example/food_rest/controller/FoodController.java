package com.example.food_rest.controller;

import com.example.food_rest.api.ProductResponse;
import com.example.food_rest.dto.FoodDTO;
import com.example.food_rest.entity.FoodEntity;
import com.example.food_rest.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api")
public final class FoodController {

    FoodService foodService;
    private static final Integer STATUS_OK = 200;
    private static final Integer STATUS_ERROR = 500;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("foods")
    public ProductResponse createFood(@RequestBody final FoodDTO foodDTO) {
        try {
            foodService.create(foodDTO);
            return ProductResponse.builder()
                    .status(STATUS_OK)
                    .message("new food is created")
                    .build();
        } catch (RuntimeException e) {
            return getFailureProductResponse(e.getMessage());
        }
    }

    @PatchMapping("foods")
    public ProductResponse updateFood(@RequestBody final FoodDTO foodDTO, @PathParam("id") Long id) {
        try {
            foodService.update(foodDTO, id);
            return ProductResponse.builder()
                    .status(STATUS_OK)
                    .message("update food is success")
                    .build();
        } catch (RuntimeException e) {
            return getFailureProductResponse(e.getMessage());
        }

    }

    @GetMapping("test")
    public String test() {
        return "it works!";
    }

    @GetMapping("foods")
    public List<FoodEntity> getAll(@PathParam("offset") Integer offset) {
        return foodService.findAll(offset);
    }

    private ProductResponse getFailureProductResponse(String message) {
        return ProductResponse.builder()
                .status(STATUS_ERROR)
                .message(message)
                .build();
    }


}

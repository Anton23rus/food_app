package com.example.food_rest.api;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;
import java.util.Map;

@Builder
@ToString
@Jacksonized
@Getter
public class ProductResponse {
    Integer status;
    String message;
    Map<String,Object> data;

}

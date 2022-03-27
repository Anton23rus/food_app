package com.example.food_rest.exceptions;

public class DataBaseNotAvailableException extends RuntimeException{
    public DataBaseNotAvailableException(String dataBase) {
        super(String.format("Database %s is not available", dataBase));
    }
}

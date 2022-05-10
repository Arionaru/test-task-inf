package com.example.testtaskinf.exception;

public class MoreThanOneCityFoundException extends RuntimeException {
    public MoreThanOneCityFoundException(String propertyValue) {
        super(String.format("Для значения %s было найдено больше одного города!", propertyValue));
    }
}

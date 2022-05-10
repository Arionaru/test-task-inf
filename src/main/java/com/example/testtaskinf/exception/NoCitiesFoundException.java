package com.example.testtaskinf.exception;

public class NoCitiesFoundException extends RuntimeException {
    public NoCitiesFoundException() {
        super("Не найдено ни одного города!");
    }
}

package com.microservice.stock_ms.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message){
        super(message);
    }
}

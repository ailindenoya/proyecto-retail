package com.proyectoaccenture.demo.exceptions;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
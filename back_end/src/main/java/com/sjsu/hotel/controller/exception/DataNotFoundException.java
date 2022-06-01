package com.sjsu.hotel.controller.exception;

public class DataNotFoundException extends RuntimeException {
    private final String message;
    private final int id;

    public DataNotFoundException(String message, int id) {
        this.message = message;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}

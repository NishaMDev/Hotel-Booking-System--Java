package com.sjsu.hotel.controller.error;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class ApiError {
    private Date timestamp;
    private String message;
    private String details;
    private String path;

    public ApiError(Date timestamp, String message, String details, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.path = path;
    }


}

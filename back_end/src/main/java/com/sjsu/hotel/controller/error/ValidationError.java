package com.sjsu.hotel.controller.error;

import java.util.Date;
import java.util.Map;

public class ValidationError {
    private Date timestamp;
    private String message;
    private Map<String,String> details;
    private String path;

    public ValidationError(Date timestamp, String message, Map<String, String> details,String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public String getPath() {
        return path;
    }
}

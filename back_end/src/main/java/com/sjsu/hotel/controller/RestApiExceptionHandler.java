package com.sjsu.hotel.controller;

import com.sjsu.hotel.controller.error.ApiError;
import com.sjsu.hotel.controller.error.ValidationError;
import com.sjsu.hotel.controller.exception.DataNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ValidationError validationError = new ValidationError(new Date(), "Validation failed for "+ex.getBindingResult().getObjectName(),errors,getUriPath(request));
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = new ApiError(new Date(),"Internal Error",ex.getMessage(), getUriPath(request));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private String getUriPath(WebRequest request) {
        return request.getDescription(false).replace("uri=","");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleDataNotFound(EmptyResultDataAccessException ex,  WebRequest request){
        ApiError error = new ApiError(new Date(),ex.getLocalizedMessage(),ex.getMessage(),getUriPath(request));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFound(DataNotFoundException ex,  WebRequest request){
        ApiError error = new ApiError(new Date(),ex.getMessage(),ex.getMessage() + " with id "+ex.getId(),getUriPath(request));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}

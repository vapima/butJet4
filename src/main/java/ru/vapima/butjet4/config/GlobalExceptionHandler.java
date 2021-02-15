package ru.vapima.butjet4.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExeptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ExeptionResponse exeptionResponse = new ExeptionResponse(e.getMessage());
        log.warn(e.getMessage());
        return new ResponseEntity<>(exeptionResponse, HttpStatus.METHOD_NOT_ALLOWED); //TODO нежнее)
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExeptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        ExeptionResponse exeptionResponse = new ExeptionResponse(e.getMessage());
        log.warn(e.getMessage());
        return new ResponseEntity<>(exeptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExeptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ExeptionResponse exeptionResponse = new ExeptionResponse(e.getMessage());
        log.warn(e.getMessage());
        return new ResponseEntity<>(exeptionResponse, HttpStatus.BAD_REQUEST);
    }

}

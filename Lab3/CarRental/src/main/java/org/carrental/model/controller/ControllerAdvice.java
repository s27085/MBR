package org.carrental.model.controller;

import org.carrental.exception.CarNotFoundException;
import org.carrental.exception.ValidationException;
import org.carrental.model.request.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ControllerAdvice {
        @ExceptionHandler(ValidationException.class)
        public ResponseEntity<ErrorResponse> handleValidationexception(ValidationException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessageWithField());
            if(!e.getErrors().isEmpty()){
                errorResponse = new ErrorResponse("Validation error", e.getErrors());
            }
            return ResponseEntity.badRequest().body(errorResponse);
        }
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarNotFoundException(CarNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}

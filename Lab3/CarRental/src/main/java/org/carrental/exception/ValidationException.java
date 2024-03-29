package org.carrental.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationException extends RuntimeException{
    private String field;
    private String message;
    @Getter
    private Map<String, String> errors;
    public String getMessageWithField() {
        return field + " " + message;
    }

    public ValidationException(String field, String message){
        super(message);
        this.message = message;
        this.field = field;
    }
    public ValidationException(Map<String, String> errors){
        this.errors = errors;
    }
}

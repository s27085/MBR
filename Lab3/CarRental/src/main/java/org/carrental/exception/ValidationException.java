package org.carrental.exception;

public class ValidationException extends RuntimeException{
    private String field;
    private String message;
    public String getMessageWithField() {
        return field + " " + message;
    }

    public ValidationException(String field, String message){
        super(message);
        this.message = message;
        this.field = field;
    }
}

package org.exception;

public class ValidationException extends RuntimeException{
    private String field;
    private String message;

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ValidationException(String field, String message){
        super(message);
        this.message = message;
        this.field = field;
    }
}

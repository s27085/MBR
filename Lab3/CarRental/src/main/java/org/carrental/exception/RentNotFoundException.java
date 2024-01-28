package org.carrental.exception;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(String s) {
        super(s);
    }
}

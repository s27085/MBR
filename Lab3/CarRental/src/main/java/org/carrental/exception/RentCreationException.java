package org.carrental.exception;
public class RentCreationException extends RuntimeException {
    public RentCreationException(String s, Exception reason) {
        super(s, reason);
    }
    public RentCreationException(String s) {
        super(s);
    }
}

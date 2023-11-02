package org.carrental.exception;

public class CarRentedException extends RuntimeException {
    public CarRentedException(String carIsRented) {
        super(carIsRented);
    }
}

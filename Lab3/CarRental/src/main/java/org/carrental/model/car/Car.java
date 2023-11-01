package org.carrental.model.car;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Car {
    private Integer id;
    private String make;
    private String model;
    private String vin;
    private CarStatus carStatus;
    private CarClass carClass;

    public Car(Integer id, String make, String model, String vin, CarStatus carStatus, CarClass carClass) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.carStatus = carStatus;
        this.carClass = carClass;
    }
}

package org.carrental.model.car;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private Integer id;
    private String make;
    private String model;
    private String vin;
    private CarStatus carStatus;
    private CarClass carClass;
    private Integer price;
}

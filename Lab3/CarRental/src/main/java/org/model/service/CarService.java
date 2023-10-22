package org.model.service;

import org.exception.ValidationException;
import org.model.repository.CarRepository;
import org.model.car.Car;

public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Car create(Car car){
        if(car.getMake().isBlank()){
            throw new ValidationException("make", "Cannot be blank");
        }
        if(car.getModel().isBlank()){
            throw new ValidationException("model", "Cannot be blank");
        }
        if(car.getVin().isBlank()){
            throw new ValidationException("vin", "Cannot be blank");
        }
        if(car.getVin().length() != 17){
            throw new ValidationException("vin", "Vin length must be equal to 17");
        }
        return carRepository.create(car);
    }
}

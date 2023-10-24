package org.carrental.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carrental.exception.*;
import org.carrental.model.car.Car;
import org.carrental.model.car.CarStatus;
import org.carrental.model.client.Client;
import org.carrental.model.repository.CarRepository;

import java.util.List;

public class CarService {
    private static final Logger logger = LogManager.getLogger();
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        logger.info("Car service initialized!");
        this.carRepository = carRepository;
    }

    public Car create(Car car){
        logger.info("Attempt to create a car...");
        if(car.getMarka().isBlank()){
            throw new ValidationException("make", "Cannot be blank");
        }
        if(car.getModel().isBlank()){
            throw new ValidationException("model", "Cannot be blank");
        }
        if(car.getVin().isBlank()){
            throw new ValidationException("vin", "Cannot be blank");
        }
        if(car.getVin().length() != 3){
            throw new ValidationException("vin", "Vin length must be equal to 3");
        }
        return carRepository.create(car);
    }

    public List<Car> getAvailableCars(){
        return carRepository.getByStatus(CarStatus.AVAILABLE);
    }
    public List<Car> getAllCars(){
        return carRepository.GetAll();
    }
    public Car getById(Integer id){
        if(id == null){
            throw new ValidationException("id", "cannot be null");
        }
        return carRepository.getCarById(id)
                .orElseThrow( () -> new CarNotFoundException("id doesn't match any car"));
    }
//    public boolean rentCar(Integer clientID, Integer carID){    }
}

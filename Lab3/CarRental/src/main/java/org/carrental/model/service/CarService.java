package org.carrental.model.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carrental.exception.*;
import org.carrental.model.car.Car;
import org.carrental.model.car.CarStatus;
import org.carrental.model.repository.CarRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CarService {
    private static final Logger logger = LogManager.getLogger();
    private final CarRepository carRepository;


    public Car create(Car car){
        logger.info("Attempting to create a car...");
        validateCarFields(car);
        logger.info("Car created successfully");
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

    public void removeCar(Integer id){
            logger.info("Attempting to remove a car...");
            if(id == null){
                throw new ValidationException("id", "cannot be null");
            }
            Optional<Car> carToRemove = carRepository.getCarById(id);
            if(carToRemove.isEmpty()){
                throw new CarNotFoundException("id doesn't match any car");
            }
            if(carToRemove.get().getCarStatus() == CarStatus.RENTED){
                throw new CarRentedException("Cannot remove a rented car");
            }
            logger.info("Car removed successfully");
            carRepository.removeById(id);
        }
        public Car modifyCar (Car car){
            logger.info("Attempting to modify a car...");
            validateCarFields(car);
            logger.info("Car modified successfully");
            return carRepository.modify(car)
                    .orElseThrow( () -> new CarNotFoundException("Id doesn't match any car"));
        }

    private static void validateCarFields(Car car) {
        Map<String, String> errors = new HashMap<>();
        if(car.getMake() == null || car.getMake().isBlank()){
            errors.put("make", "Cannot be null or blank");
        }
        if( car.getModel() == null || car.getModel().isBlank()){
            errors.put("model", "Cannot be null or blank");
        }
        if(car.getVin() == null || car.getVin().isBlank()){
            errors.put("vin", "Cannot be null or blank");
        }
        if(car.getVin().length() != 3){
            errors.put("vin", "Vin length must be equal to 3");
        }
        if(car.getCarClass()==null){
            errors.put("carClass", "Cannot be blank");
        }
        if(car.getCarStatus()==null){
            errors.put("carStatus", "Cannot be blank");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public boolean ifCarAvailable(Car selectedCar) {
        return carRepository.getCarById(selectedCar.getId()).get().getCarStatus() == CarStatus.AVAILABLE;
    }
}

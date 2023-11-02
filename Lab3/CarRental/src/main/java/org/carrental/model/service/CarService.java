package org.carrental.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carrental.exception.*;
import org.carrental.model.car.Car;
import org.carrental.model.car.CarStatus;
import org.carrental.model.client.Client;
import org.carrental.model.repository.CarRepository;
import org.carrental.model.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

public class CarService {
    private static final Logger logger = LogManager.getLogger();
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public CarService(CarRepository carRepository, ClientRepository clientRepository){
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

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
    public List<Client> getAllClients(){
        return clientRepository.getAll();
    }
    public Car getById(Integer id){
        if(id == null){
            throw new ValidationException("id", "cannot be null");
        }
        return carRepository.getCarById(id)
                .orElseThrow( () -> new CarNotFoundException("id doesn't match any car"));
    }
    public Client register(Client client){
        logger.info("Attempting to register a client...");
        validateClientFields(client);
        logger.info("Client registered successfully");
        return clientRepository.create(client);
    }

    public void removeCar(Integer id){
            logger.info("Attempting to remove a car...");
            if(id == null){
                throw new ValidationException("id", "cannot be null");
            }
            Optional<Car> carToRemove = carRepository.getCarById(id);
            if(carToRemove.isEmpty()){
                throw new CarNotFoundException("id doesn't match any car");
            };
            if(carToRemove.get().getCarStatus() == CarStatus.RENTED){
                throw new CarRentedException("Cannot remove a rented car");
            }
            logger.info("Car removed successfully");
            carRepository.removeById(id);
        }
        public void removeClient(Integer id){
            if(id == null){
                throw new ValidationException("id", "cannot be null");
            }
            clientRepository.removeById(id);
        }
        public Car modifyCar (Car car){
            logger.info("Attempting to modify a car...");
            validateCarFields(car);
            logger.info("Car modified successfully");
            return carRepository.modify(car)
                    .orElseThrow( () -> new CarNotFoundException("Id doesn't match any car"));
        }
        public void modifyClient (Client client){
            logger.info("Attempting to modify a client...");
            validateClientFields(client);
            logger.info("Client modified successfully");
            clientRepository.modify(client)
                    .orElseThrow(() -> new ClientNotFoundException("Id doesn't match any client"));
        }

    private static void validateCarFields(Car car) {
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
        if(car.getCarClass()==null){
            throw new ValidationException("carClass", "Cannot be blank");
        }
        if(car.getCarStatus()==null){
            throw new ValidationException("carStatus", "Cannot be blank");
        }
    }
    private static void validateClientFields(Client client) {
        if(client.getName().isBlank()){
            throw new ValidationException("name", "Cannot be blank");
        }
        if(client.getGender() == null){
            throw new ValidationException("gender", "Cannot be blank");
        }
    }
}


//    public boolean rentCar(Integer clientID, Integer carID){    }

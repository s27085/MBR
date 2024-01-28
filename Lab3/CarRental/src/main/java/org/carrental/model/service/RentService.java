package org.carrental.model.service;

import org.carrental.exception.*;
import org.carrental.model.car.Car;
import org.carrental.model.client.Client;
import org.carrental.model.rent.Rent;
import org.carrental.model.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class RentService {
    private final CarService carService;
    private final ClientService clientService;
    private final RentRepository rentRepository;

    public RentService(CarService carService, ClientService clientService, RentRepository rentRepository){
        this.clientService = clientService;
        this.rentRepository = rentRepository;
        this.carService = carService;
    }

    public Rent createRent(Integer carID, Integer clientID, LocalDate startDate, Integer rentLength){
        validateRentLength(startDate, rentLength);
        Car selectedCar;
        try {
            selectedCar = carService.getById(carID);
        }catch (CarNotFoundException ex){
            throw new RentCreationException("Couldn't create rent without car", ex);
        }
        carService.ifCarAvailable(selectedCar);
        try {
            Client selectedClient = clientService.getClientById(clientID);
        }catch (ClientNotFoundException ex){
            throw new RentCreationException("Couldn't create rent without client", ex);
        }
        Double rentPrice = getRentPrice(rentLength, selectedCar);
        Rent rent = new Rent(carID, clientID, startDate, startDate.plusDays(rentLength), rentPrice);
        return rentRepository.rentCar(rent);
    }

    private static double getRentPrice(Integer rentLength, Car selectedCar) {
        return rentLength * selectedCar.getPrice();
    }

    private void validateRentLength(LocalDate startDate, Integer rentLength) {
        if(startDate.isBefore(LocalDate.now())) throw new ValidationException("startDate", "Today date precedes rent start date");
        if(rentLength < 1) throw new ValidationException("rentLength", "Rent length is too short");
    }

    public Rent getRentById(Integer id){
        if(id == null){
            throw new ValidationException("id", "id cannot be null");
        }
        return rentRepository.getRentById(id)
                .orElseThrow(() -> new RentNotFoundException("Id doesn't match any rent"));
    }
    public List<Rent> getAllRents(){
        return rentRepository.getAll();
    }
}

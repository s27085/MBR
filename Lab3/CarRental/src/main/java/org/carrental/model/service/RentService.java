package org.carrental.model.service;

import lombok.RequiredArgsConstructor;
import org.carrental.exception.CarNotFoundException;
import org.carrental.exception.RentCreationException;
import org.carrental.model.car.Car;
import org.carrental.model.car.CarStatus;
import org.carrental.model.client.Client;
import org.carrental.model.rent.Rent;
import org.carrental.model.repository.RentRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
public class RentService {
    private final CarService carService;
    private final RentRepository rentRepository;

    public Rent createRent(Integer carID, Integer clientID, LocalDate startDate, Integer rentLength){
        //TODO dodać walidacje
        Car selectedCar;
        try {
            selectedCar = carService.getById(carID);
        }catch (CarNotFoundException ex){
            throw new RentCreationException("Couldn't create rent", ex);
        }
        if(selectedCar.getCarStatus() != CarStatus.AVAILABLE){
            throw new RentCreationException("Car is not available");
        }
        Client selectedClient = carService.getClientById(clientID);
        double rentPrice = rentLength * selectedCar.getPrice();
        Rent rent = new Rent(carID, clientID, startDate, startDate.plusDays(rentLength), rentPrice);
        return rentRepository.rentCar(rent);
    }
}

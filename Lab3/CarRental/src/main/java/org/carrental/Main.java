package org.carrental;

import org.carrental.model.car.Car;
import org.carrental.model.car.CarClass;
import org.carrental.model.car.CarStatus;
import org.carrental.model.client.Client;
import org.carrental.model.client.Gender;
import org.carrental.model.repository.CarRepository;

import java.util.List;

import org.carrental.model.repository.ClientRepository;
import org.carrental.model.service.CarService;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository();
        ClientRepository clientRepository = new ClientRepository();
        CarService carService = new CarService(carRepository, clientRepository);
        Car car = new Car(
        null, "Volkswagen", "10A", "311", CarStatus.AVAILABLE, CarClass.PREMIUM);
        Car car2 = new Car(
                null, "Volkswagen", "10A", "311", CarStatus.AVAILABLE, CarClass.PREMIUM);
        carService.create(car);
        carService.create(car2);
        List<Car> availableCars = carService.getAvailableCars();
        System.out.println(availableCars);
        Client client1 = new Client(null, "Jan Kowalski", Gender.MALE);
        carService.register(client1);
        car.setCarStatus(CarStatus.RENTED);
        carService.removeCar( car2.getId());
        carService.modifyClient(new Client(0, "grzegorz", Gender.MALE));
        System.out.println(carService.getAllClients());
        /*
        TODO:
            1. obsługa pozostałych metod z car repo (zostało modify)
            2. model, repo i serwis dla klientów check
            3. lombok check
            4. logi check
            kolejny zjazd: testy jednostkowe, rezerwacje
        */
    }
}
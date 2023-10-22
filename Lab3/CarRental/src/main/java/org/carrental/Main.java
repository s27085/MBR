package org.carrental;

import org.carrental.model.car.Car;
import org.carrental.model.car.CarClass;
import org.carrental.model.car.CarStatus;
import org.carrental.model.repository.CarRepository;

import java.util.List;
import org.carrental.model.service.CarService;

public class Main {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository();
        CarService carService = new CarService(carRepository);
        Car car = new Car(
        null, "Volkswagen", "10A", "311", CarStatus.AVAILABLE, CarClass.PREMIUM);
        Car car2 = new Car(
                null, "Volkswagen", "10A", "311", CarStatus.AVAILABLE, CarClass.PREMIUM);
        carService.create(car);
        carService.create(car2);
        List<Car> availableCars = carService.getAvailableCars();
        System.out.println(availableCars);

        /*
        1. obsługa pozostanych metod z car repo
        2. model, repo i servis dla klientów
        3. lombok
        4. logi
        kolejny zjazd: testy jednostkowe, rezerwacje
        */
    }
}
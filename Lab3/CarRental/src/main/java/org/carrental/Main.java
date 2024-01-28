package org.carrental;

import org.carrental.model.car.Car;
import org.carrental.model.car.CarClass;
import org.carrental.model.car.CarStatus;

import org.carrental.model.repository.CarRepository;
import org.carrental.model.service.CarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        //create car service object
        CarService carService = context.getBean("carService", CarService.class);
        carService.create(new Car(null, "volkswagen", "bleble", "12A", CarStatus.AVAILABLE, CarClass.PREMIUM, 900));
        System.out.println(carService.getById(0));
    }
}
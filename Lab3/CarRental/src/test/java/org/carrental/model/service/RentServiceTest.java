package org.carrental.model.service;

import org.carrental.model.car.Car;
import org.carrental.model.car.CarClass;
import org.carrental.model.car.CarStatus;
import org.carrental.model.client.Client;
import org.carrental.model.client.Gender;
import org.carrental.model.rent.Rent;
import org.carrental.model.repository.CarRepository;
import org.carrental.model.repository.ClientRepository;
import org.carrental.model.repository.RentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RentServiceTest {
    private RentService rentService;
    private CarService carService;
    private ClientService clientService;

    @BeforeEach
    public void setUp(){
        carService = new CarService(new CarRepository());
        clientService = new ClientService(new ClientRepository());
        rentService = new RentService(carService, clientService, new RentRepository());
    }

    @ParameterizedTest
    @MethodSource("provideValidRents")
    public void shouldRentRepositoryHaveOneRecord_whenRentRegistered(Client client, Car car){
        carService.create(car);
        clientService.register(client);
        rentService.createRent(0, 0, LocalDate.of(2023, 12, 11), 10);
        assertEquals(rentService.getAllRents().size(), 1);
    }


    public static Stream<Arguments> provideValidRents() {
        return Stream.of(
                Arguments.of(new Client(null, "jan", Gender.FEMALE),
                        new Car(null, "skoda", "10A", "123", CarStatus.AVAILABLE, CarClass.PREMIUM, 200))
        );
    }
    }
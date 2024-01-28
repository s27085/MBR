package org.carrental.model.service;

import org.carrental.exception.CarNotFoundException;
import org.carrental.exception.ValidationException;
import org.carrental.model.car.Car;
import org.carrental.model.car.CarClass;
import org.carrental.model.car.CarStatus;
import org.carrental.model.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private static CarService carService;
    @BeforeEach
    void setUp() {
        carService = new CarService(new CarRepository());
    }

    @Test
    void shouldCreateNewCar(){
        Car car = new Car(
           null, "volkswagen", "bleble", "12A", CarStatus.AVAILABLE, CarClass.PREMIUM, 900
                );
        Car result = assertDoesNotThrow(() -> carService.create(car));
        assertEquals(result, car);
    }

    @Test
    void shouldNotReturnAnyCar(){
        List<Car> result = carService.getAllCars();
        assertEquals(result.size(), 0);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCarsAndMessages")
    void shouldNotCreateNewCar(Car car, String resultMessage){
        ValidationException result = assertThrows(ValidationException.class, ()->carService.create(car));
        assertEquals(result.getMessage(), resultMessage);
    }
    @Test
    void shouldNotReturnCarById(){
        assertThrows(CarNotFoundException.class, () -> carService.getById(1));
    }

    public static Stream<Arguments> provideInvalidCarsAndMessages(){
        return Stream.of(
                Arguments.of(new Car(
                        null, "volkswagen", null, "12a", CarStatus.AVAILABLE, CarClass.PREMIUM, 15
                ), "Cannot be null or blank"),
                Arguments.of(new Car(
                        null, "volkswagen", "bleble", "12dwada", CarStatus.AVAILABLE, CarClass.PREMIUM, 15
                ), "Vin length must be equal to 3"),
                Arguments.of(new Car(
                        null, "volkswagen", "bleble", "", CarStatus.AVAILABLE, CarClass.PREMIUM, 15
                ), "Cannot be null or blank"),
                Arguments.of(new Car(
                        null, null, "bleble", "12dwada", CarStatus.AVAILABLE, CarClass.PREMIUM, 15
                ), "Cannot be null or blank"),
                Arguments.of(new Car(
                        null, "volkswagen", "bleble", "12a", CarStatus.AVAILABLE, null, 15
                ), "Cannot be blank"),
                Arguments.of(new Car(
                        null, "volkswagen", "bleble", "12a", null, CarClass.PREMIUM, 15
                ), "Cannot be blank")
        );
    }
}
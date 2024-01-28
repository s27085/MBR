package org.carrental.model.controller;

import lombok.RequiredArgsConstructor;
import org.carrental.exception.CarNotFoundException;
import org.carrental.model.car.Car;
import org.carrental.model.service.CarService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> allCars = carService.getAllCars();
        return ResponseEntity.ok(allCars);
    }
    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars(){
        List<Car> availableCars = carService.getAvailableCars();
        return ResponseEntity.ok(availableCars);
    }
    @GetMapping("/{id}/make")
    public ResponseEntity<String> getCarByPathvariable(@PathVariable Integer id){
        Car car = carService.getById(id);
        return ResponseEntity.ok(car.getMake());
    }
    //localhost:8080/car?id=1
    //
    @GetMapping
    public ResponseEntity<Car> getCarByIdParam(@RequestParam(required = false) Integer id){
        Car car;
        try {
            car = carService.getById(id);
        }catch (CarNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }
    //localhost:8080/car/create
    @PostMapping("/create")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car createdCar = carService.create(car);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(createdCar);
    }
}

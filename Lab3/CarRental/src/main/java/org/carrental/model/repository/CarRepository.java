package org.carrental.model.repository;

import org.carrental.model.car.Car;
import org.carrental.model.car.CarStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarRepository {
    private List<Car> carList = new ArrayList<>();

    public Car create(Car car){
        car.setId(carList.size());
        carList.add(car);
        return car;
    }

    public Optional<Car> getCarById(Integer id){
        return carList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }
    public List<Car> getByStatus(CarStatus carStatus){
        return carList.stream()
                .filter(it -> it.getCarStatus().equals(carStatus))
                .collect(Collectors.toList());
    }

    public void removeById(Integer id ){
        Optional<Car> car = getCarById(id);
        car.ifPresent(it -> carList.remove(it));
    }

    public void removeAll(){
        carList = new ArrayList<>();
    }
    public Optional<Car> modify(Car car){
        Optional<Car> carToModify = getCarById(car.getId());
        return carToModify.map(it ->{
            it.setMarka(car.getMarka());
            it.setCarClass(car.getCarClass());
            it.setCarStatus(car.getCarStatus());
            it.setModel(car.getModel());
            it.setVin(car.getVin());
            return it;
        });
    }
    public List<Car> GetAll(){
        return carList;
    }
}

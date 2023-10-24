package org.carrental.model.repository;

import org.carrental.model.car.Car;
import org.carrental.model.car.CarStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarRepository extends Repository{
    private List<Car> carList = new ArrayList<Car>();

    public List<Car> getByStatus(CarStatus carStatus){
        return carList.stream()
                .filter(it -> it.getCarStatus().equals(carStatus))
                .collect(Collectors.toList());
    }
    public Optional<Car> modify(Car car){
        Optional<Car> carToModify = getById(car.getId());
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

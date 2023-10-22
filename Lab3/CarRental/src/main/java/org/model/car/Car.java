package org.model.car;

public class Car {
    private Integer id;
    private String make;
    private String model;
    private String vin;
    private CarStatus carStatus;
    private CarClass carClass;

    public Car(Integer id, String make, String model, String vin, CarStatus carStatus, CarClass carClass) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.carStatus = carStatus;
        this.carClass = carClass;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public Integer getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public CarClass getCarClass() {
        return carClass;
    }
}

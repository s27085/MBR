package org.carrental.model.car;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carrental.model.repository.RepositoryEntry;

@EqualsAndHashCode(callSuper = true)
@Data
public class Car extends RepositoryEntry {
    private String marka;
    private String model;
    private String vin;
    private CarStatus carStatus;
    private CarClass carClass;
    private Boolean occupied;
}

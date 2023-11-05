package org.carrental.model.rent;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
@Data
@RequiredArgsConstructor
public class Rent {
    private Integer id;
    private final Integer carId;
    private final Integer clientId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Double price;
}

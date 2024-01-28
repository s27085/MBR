package org.carrental.model.repository;

import lombok.RequiredArgsConstructor;
import org.carrental.exception.ValidationException;
import org.carrental.model.rent.Rent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class RentRepository {
    private List<Rent> rentList;
    public Rent rentCar(Rent rent){
        rent.setId(rentList.size());
        rentList.add(rent);
        return rent;
    }
    public Optional<Rent> getRentById(Integer id){
        if(id == null){
            throw new ValidationException("id", "id can't be null");
        }
        return rentList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    public List<Rent> getAll(){
        return rentList;
    }

}

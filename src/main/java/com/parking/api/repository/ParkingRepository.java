package com.parking.api.repository;

import com.parking.api.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    public List<Parking> findByPlate(String plate);

}

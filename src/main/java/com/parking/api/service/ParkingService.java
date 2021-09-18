package com.parking.api.service;

import com.parking.api.model.Parking;
import com.parking.api.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingService {
    @Autowired
    ParkingRepository repository;

//    public Parking findByPlate(String plate) {
//        repository.findByPlate
//    }
}

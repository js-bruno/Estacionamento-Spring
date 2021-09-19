package com.parking.api.service;

import com.parking.api.model.Parking;
import com.parking.api.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingService {
    @Autowired
    ParkingRepository repository;

    public List<Parking> findAll() {
        return repository.findAll();
    }

    public List<Parking> findByPlate(String plate) {
        List<Parking> parkins = repository.findByPlate(plate);
        return parkins;
    }

    public void save(Parking park) {
        repository.save(park);
    }

    public ResponseEntity<?> checkAndSave(String plate) {
        List<Parking> parkings = this.findByPlate(plate);
        if(!parkings.isEmpty()) {
            Parking lastPark = parkings.get(parkings.size() - 1);
            if (!lastPark.isOut()) {
                return new ResponseEntity<>("Placa Já Esta Estacionada", HttpStatus.NOT_ACCEPTABLE);
            }
        }

        long millis=System.currentTimeMillis();
        Date dateEntry = new java.util.Date(millis);

        Parking parking = new Parking(plate, "60 minutos", false, false, dateEntry, null);
        this.save(parking);
        return new ResponseEntity<>(parking, HttpStatus.CREATED);
    }

    public Parking checkAndUpdatePay(String plate) {
        List<Parking> park=this.findByPlate(plate);
        Parking lastPark = park.get(park.size() - 1);
        lastPark.setPaid(true);
        this.save(lastPark);
        return lastPark;
    }

    public ResponseEntity<?> checkAndUpdateOut(String plate) {
        List<Parking> park=this.findByPlate(plate);
        Parking lastPark = park.get(park.size() - 1);
        if (lastPark.isPaid()) {
            long millis=System.currentTimeMillis();
            Date dateOut = new java.util.Date(millis);

            lastPark.setOut(true);
            lastPark.setDateTimeOut(dateOut);
            this.save(lastPark);
            return new ResponseEntity<>(lastPark, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>("Estacionamento ainda não foi pago", HttpStatus.NOT_MODIFIED);
        }
    }


}

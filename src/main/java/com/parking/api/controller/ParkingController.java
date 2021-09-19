package com.parking.api.controller;

import com.parking.api.model.Parking;
import com.parking.api.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parking")

public class ParkingController {

    @Autowired
    private ParkingRepository parkingRepository;

    @GetMapping("/")
    public List<Parking> listParkings() {
        return parkingRepository.findAll();
    }

    @GetMapping("/{plate}")
    public List<Parking> getParking(@PathVariable("plate") String plate) {
        return parkingRepository.findByPlate(plate);
    }

    @PostMapping("/{plate}")
    public String reserveParking(@PathVariable("plate") String plate) {
        List<Parking> parkings = this.parkingRepository.findByPlate(plate);
        if(!parkings.isEmpty()) {
            Parking lastPark = parkings.get(parkings.size() - 1);
            if (!lastPark.isOut()) {
                return "Veiculo já esta estacionado";
            }
        }

        long millis=System.currentTimeMillis();
        Date dateEntry = new java.util.Date(millis);

        Parking parking = new Parking(plate, "60 minutos", false, false, dateEntry, null);
        this.parkingRepository.save(parking);
//        return this.parkingRepository.save(parking).getId();
        return "Veiculo Estacionado com sucesso";
    }

    @PatchMapping("/{plate}/pay")
    public Parking payParking(@PathVariable("plate") String plate) {
        List<Parking> park=this.parkingRepository.findByPlate(plate);
        Parking lastPark = park.get(park.size() - 1);
        lastPark.setPaid(true);
        this.parkingRepository.save(lastPark);
        return lastPark;
    }

    @PatchMapping("/{plate}/out")
    public String outParking(@PathVariable("plate") String plate) {
        List<Parking> park=this.parkingRepository.findByPlate(plate);
        Parking lastPark = park.get(park.size() - 1);
        if (lastPark.isPaid()) {
            long millis=System.currentTimeMillis();
            Date dateOut = new java.util.Date(millis);

            lastPark.setOut(true);
            lastPark.setDateTimeOut(dateOut);
            this.parkingRepository.save(lastPark);
            return "Muito Obrigado, Volte Sempre.";
        }
        else {
            return "O Estacionamento Ainda Não Foi Pago";
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });

        return errors;
    }

}

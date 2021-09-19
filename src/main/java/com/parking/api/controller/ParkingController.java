package com.parking.api.controller;

import com.parking.api.model.Parking;
import com.parking.api.service.ParkingService;
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
    private ParkingService parkingService;

    @GetMapping("/")
    public List<Parking> listParkings() {
        return parkingService.findAll();
    }

    @GetMapping("/{plate}")
    public List<Parking> getParking(@PathVariable("plate") String plate) {
        return parkingService.findByPlate(plate);
    }

    @PostMapping("/{plate}")
    public ResponseEntity<?> reserveParking(@PathVariable("plate") String plate) {
        return this.parkingService.checkAndSave(plate);
    }

    @PatchMapping("/{plate}/pay")
    public Parking payParking(@PathVariable("plate") String plate) {
        return this.parkingService.checkAndUpdatePay(plate);
    }

    @PatchMapping("/{plate}/out")
    public ResponseEntity<?> outParking(@PathVariable("plate") String plate) {
        return this.parkingService.checkAndUpdateOut(plate);

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

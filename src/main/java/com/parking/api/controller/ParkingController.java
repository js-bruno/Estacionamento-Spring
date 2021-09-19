package com.parking.api.controller;

import com.parking.api.model.Parking;
import com.parking.api.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parking")
//@Api("")

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
//    TODO:Não deixa ele cadastrar a mesma plac
    public Long reserveParking(@PathVariable("plate") String plate) {
        Parking parking = new Parking(plate, "60 minutos", false, false);
        this.parkingRepository.save(parking);
//        return parki;
        return this.parkingRepository.save(parking).getId();
    }

    @PatchMapping("/{plate}/pay")
    public Parking payParking(@PathVariable("plate") String plate) {
        Parking park=this.parkingRepository.findByPlate(plate).get(0);
        park.setPaid(true);
        this.parkingRepository.save(park);
        return park;
    }

    @PatchMapping("/{plate}/out")
    public String outParking(@PathVariable("plate") String plate) {
        Parking park=this.parkingRepository.findByPlate(plate).get(0);
        if (park.isPaid()) {
            park.setOut(true);
            this.parkingRepository.save(park);
            return "Obrigado";
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

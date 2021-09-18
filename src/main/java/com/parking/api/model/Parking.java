package com.parking.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Parking {
    public Parking(String plate, String time, boolean paid, boolean out) {
        this.plate = plate;
        this.time = time;
        this.paid = paid;
        this.out = out;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String plate;
    private String time;
    private boolean paid;
    private boolean out;


}

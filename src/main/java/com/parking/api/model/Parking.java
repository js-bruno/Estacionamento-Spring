package com.parking.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Parking {
    public Parking(String plate, String time, boolean paid, boolean out, Date dateTimeEntry, Date dateTimeOut) {
        this.plate = plate;
        this.time = time;
        this.paid = paid;
        this.out = out;
        this.dateTimeEntry = dateTimeEntry;
        this.dateTimeOut = dateTimeOut;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Pattern(regexp = "[a-z]{3}[-][0-9]{4}", message = "Formato da placa não inserido corretamente")
    private String plate;
    private String time;
    private boolean paid;
    private boolean out;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateTimeEntry;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateTimeOut;


}

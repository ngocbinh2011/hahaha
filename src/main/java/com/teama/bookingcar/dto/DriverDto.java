package com.teama.bookingcar.dto;

import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class DriverDto {
    private Long id;
    private String licenseNumber;
    private String phoneNumber;
    private Integer rating;
    private CarDto car;
}

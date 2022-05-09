package com.teama.bookingcar.dto;

import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private String make;
    private String model;
    private String color;
    private String convertible;
    private Integer rating;
    private String licensePlate;
}

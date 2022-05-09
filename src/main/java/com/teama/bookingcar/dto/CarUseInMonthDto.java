package com.teama.bookingcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarUseInMonthDto {
    private int carUseInMonth;
    private int month;
}

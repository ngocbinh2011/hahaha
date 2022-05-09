package com.teama.bookingcar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDto {
    private Long id;
    private Long totalCharge;
    private CustomerDto customer;
    private DriverDto driver;
    private BookingDto booking;
    private LocalDate createDate;
}

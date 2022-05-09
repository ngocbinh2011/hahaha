package com.teama.bookingcar.service;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.entity.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IBookingService extends IServiceAdapter<BookingDto>{
    List<BookingDto> getByCreateDate(LocalDate from, LocalDate to);
}

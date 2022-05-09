package com.teama.bookingcar.service.mapper.impl;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.entity.Booking;
import com.teama.bookingcar.service.mapper.AbstractDtoMapperAdapter;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper extends AbstractDtoMapperAdapter<Booking, BookingDto> {
    public BookingMapper() {
        super(Booking.class, BookingDto.class);
    }
}

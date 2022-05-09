package com.teama.bookingcar.service.mapper.impl;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.dto.CustomerDto;
import com.teama.bookingcar.dto.DriverDto;
import com.teama.bookingcar.entity.Booking;
import com.teama.bookingcar.entity.Customer;
import com.teama.bookingcar.entity.Driver;
import com.teama.bookingcar.service.mapper.AbstractDtoMapperAdapter;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper extends AbstractDtoMapperAdapter<Driver, DriverDto> {
    public DriverMapper() {
        super(Driver.class, DriverDto.class);
    }
}

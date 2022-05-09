package com.teama.bookingcar.service.mapper.impl;

import com.teama.bookingcar.dto.BookingDto;
import com.teama.bookingcar.dto.CustomerDto;
import com.teama.bookingcar.entity.Booking;
import com.teama.bookingcar.entity.Customer;
import com.teama.bookingcar.service.mapper.AbstractDtoMapperAdapter;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends AbstractDtoMapperAdapter<Customer, CustomerDto> {
    public CustomerMapper() {
        super(Customer.class, CustomerDto.class);
    }
}
